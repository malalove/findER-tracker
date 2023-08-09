package finder.tracker.service;

import finder.tracker.domain.Bed;
import finder.tracker.repository.TrackRepository;
import finder.tracker.xmlmapping.TestModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@EnableScheduling
public class TrackService {
    TrackRepository trackRepository;
    List<Bed> bedList = new ArrayList<>();

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Scheduled(cron = "1 * * * * *") // 스케줄링 주기 설정 (매 분 1초)
    public void callApiWithExceptionHandling() {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            // LocalDateTime localDateTime = LocalDateTime.now().plusHours(9);
            String formattedCurrentTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            System.out.println("Current Time: " + formattedCurrentTime);

            callApi(formattedCurrentTime);
        } catch (IOException e) {
            // IOException 예외 처리
            e.printStackTrace();
            errorHandler();
        } catch (JAXBException e) {
            // JAXBException 예외 처리
            e.printStackTrace();
            errorHandler();
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
            errorHandler();
        }
    }

    public void callApi(String time) throws IOException, JAXBException {
        // HTTP Request 생성
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire"); // URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=xGPAD7pYa1ixlJ1OQJOrXhiiNSoEJqkoVBvHYMMHW%2B9qU4qRlp8KVsF3AIEEMgYrcvsH7E1SoLcQR8P8BX6TxA%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("STAGE1","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); // 주소(시도)
        urlBuilder.append("&" + URLEncoder.encode("STAGE2","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); // 주소(시군구)
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지 번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("413", "UTF-8")); // 목록 건수
        URL url = new URL(urlBuilder.toString());

        // HTTP Request 전송
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // 현재 시간 기록
        long startTime = System.currentTimeMillis();

        // HTTP Response 상태 코드 확인
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        // HTTP Response 저장
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            // 파일 읽기 또는 네트워크 통신 중에 발생하는 예외 처리
            e.printStackTrace();
            errorHandler();
        }

        rd.close();
        conn.disconnect();

        String xmlData = sb.toString();
        // System.out.println(xmlData);

        // XML 데이터를 Java 객체로 변환 (언마샬링)
        JAXBContext jaxbContext = JAXBContext.newInstance(TestModel.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlData);
        TestModel hospitalResponse = (TestModel) unmarshaller.unmarshal(reader);
        System.out.println("Data 수 : " + hospitalResponse.getBody().getItems().getItem().size());

        bedList.clear();

        for (int i=0; i <= 412; i++) {
            String dutyName = hospitalResponse.getBody().getItems().getItem().get(i).getDutyName();
            Long hvec = hospitalResponse.getBody().getItems().getItem().get(i).getHvec();

            // System.out.println("병원: " + dutyName + ", 병상 수: " + hvec);

            try {
                bedList.add(new Bed(dutyName, time, Math.toIntExact(hvec)));
            } catch (Exception e) {
                hvec = 0L;
                bedList.add(new Bed(dutyName, time, Math.toIntExact(hvec)));
            }
        }

        trackRepository.saveAll(bedList);

        // 데이터 받아와서 저장하기까지 소요된 시간 출력
        long endTime = System.currentTimeMillis();
        System.out.println("callApi() 함수 소요 시간: " + (endTime - startTime) + "ms");
    }

    public void errorHandler() {
        // 현재 시간 기록
        long startTime = System.currentTimeMillis();

        for (Bed bed : bedList) {
            bed.increaseByOneMinute();
        }

        trackRepository.saveAll(bedList);

        // 데이터 저장하기까지 소요된 시간 출력
        long endTime = System.currentTimeMillis();
        System.out.println("errorHandler() 함수 소요 시간: " + (endTime - startTime) + "ms");
    }
}