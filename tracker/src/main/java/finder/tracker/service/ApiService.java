package finder.tracker.service;

import finder.tracker.domain.TestModel;
import lombok.Data;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.*;

@Component
@Data
@EnableScheduling
public class ApiService {
    private ThreadPoolTaskScheduler scheduler;

    public ApiService(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = new ThreadPoolTaskScheduler();
        this.scheduler.initialize();
    }

    @Scheduled(cron = "0 * * * * *") // 스케줄링 주기 설정 (매 분)
    public void callApiWithExceptionHandling() {
        try {
            callApi();
        } catch (IOException e) {
            // IOException 예외 처리
            e.printStackTrace();
        } catch (JAXBException e) {
            // JAXBException 예외 처리
            e.printStackTrace();
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
        }
    }

    public void callApi() throws IOException, JAXBException {
        // HTTP Request 생성
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire"); // URL
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=xGPAD7pYa1ixlJ1OQJOrXhiiNSoEJqkoVBvHYMMHW%2B9qU4qRlp8KVsF3AIEEMgYrcvsH7E1SoLcQR8P8BX6TxA%3D%3D"); //Service Key
        urlBuilder.append("&" + URLEncoder.encode("STAGE1","UTF-8") + "=" + URLEncoder.encode("서울특별시", "UTF-8")); // 주소(시도)
        urlBuilder.append("&" + URLEncoder.encode("STAGE2","UTF-8") + "=" + URLEncoder.encode("강남구", "UTF-8")); // 주소(시군구)
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); // 페이지 번호
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); // 목록 건수
        URL url = new URL(urlBuilder.toString());

        // HTTP Request 전송
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

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
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        String xmlData = sb.toString();
        System.out.println(xmlData);

        // XML 데이터를 Java 객체로 변환 (언마샬링)
        JAXBContext jaxbContext = JAXBContext.newInstance(TestModel.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlData);
        TestModel hospitalResponse = (TestModel) unmarshaller.unmarshal(reader);

        System.out.println("Data 수: " + hospitalResponse.getBody().getItems().getItem().size());

        String dutyName = hospitalResponse.getBody().getItems().getItem().get(0).getDutyName();
        Long hvec = hospitalResponse.getBody().getItems().getItem().get(0).getHvec();

        System.out.println("병원: " + dutyName + ", 병상 수: " + hvec);
    }
}