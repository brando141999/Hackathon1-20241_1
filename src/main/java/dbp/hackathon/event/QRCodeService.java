package dbp.hackathon.event;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QRCodeService {

    public String generateQRCode(String text) {
        return "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + text;
    }


}
