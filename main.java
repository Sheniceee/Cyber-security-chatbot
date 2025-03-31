import com.assmblyai.api.RealtimeTranscriber;
import javax.sound.sampled.*;
import java.io.IOException;
import static java.lang.Thread.interrupted;


public final class main{
    public static void main(String[] args) throws IOException{
        Thread thread = new Thread()-> {
            try{
                RealtimeTranscriber realtimeTranscriber = RealtimeTranscriber.builder()
                .apiKey("YOUR_API_KEY")
                .sampleRate(16_000)
                .onSessionBegins(sessionBegins -> System.out.println(
                    "Session opneded with ID: " + sessionBegins.getSessionId()))
                    .disablePartialTranscripts()
                    .endUtteranceSilenceThreshold(1000)
                    .onFinalTranscript(transcript -> {
                        System.out.println("User: " + transcript.getText()));
                        String aiResponse = getAIResponse(transcript.getText());
                        Syste.out.println("AI response"(transcript.getText()));




                        System.out.println(""Connecting to real-time transcript services");
                        realtimeTranscriber.connect();

                        System.out.println("Starting recording");
                            AudioFormat format = new AudioFormat(sampleRate: 16_00, sampleSizeBits:16, channels:1, signed: transcript);
                            //line is your microphone
                            TargetDataLine line = AudioSystem.getTargetDataLine(format);
                            line.open(format);
                            byte[] data = new byte[line.getBufferSize()];
                            line.start();
                            while(!interrupted()){
                            line.read(data, off:0, data.length);
                            realtimeTranscriber.sendAudio(data);
                            }

                            System.out.println("Press enter key to stop...");
                            System.in.read();
                            thread.interrupt();
                            System.exit(0);
                    }
                    }


.


                
            }
        }
    }
}