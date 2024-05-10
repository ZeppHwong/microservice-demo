package cn.demo;

import reactor.core.publisher.Flux;

import java.util.List;

public class AsynchronousFlux extends BaseFlux {

  public static void main(String[] args) {
//        CreateBridgeToFlux();
//        PushBridgeToFlux();
//        HybridPushPullModel();
//        CleanUpAfterCreateOrPush();
    HandlerFLux();
  }

  private static void CleanUpAfterCreateOrPush() {
/*        Flux<String> bridge = Flux.create(sink -> {
            sink.onRequest(n -> channel.poll(n))
                    .onCancel(() -> channel.cancel())
                    .onDispose(() -> channel.close());
        });*/
  }

  private static void HybridPushPullModel() {
/*        Flux<String> bridge = Flux.create(sink -> {
            myMessageProcessor.register(
                    new MyMessageListener<String>() {

                        public void onMessage(List<String> messages) {
                            for(String s : messages) {
                                sink.next(s);
                            }
                        }
                    });
            sink.onRequest(n -> {
                List<String> messages = myMessageProcessor.getHistory(n);
                for(String s : messages) {
                    sink.next(s);
                }
            });
        });*/
  }

  /**
   * Asynchronous but single-threaded
   */
  private static void PushBridgeToFlux() {
/*        Flux<String> bridge = Flux.push(sink -> {
            myEventProcessor.register(
                    new SingleThreadEventListener<String>() {

                        public void onDataChunk(List<String> chunk) {
                            for(String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }

                        public void processError(Throwable e) {
                            sink.error(e);
                        }
                    });
        });*/
  }

  private static void CreateBridgeToFlux() {
  /*      Flux<String> bridge = Flux.create(sink -> {
            myEventProcessor.register(
                    new MyEventListener<String>() {

                        public void onDataChunk(List<String> chunk) {
                            for(String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }
                    });
        });*/
  }

  interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);

    void processComplete();
  }

  interface SingleThreadEventListener<T> {
    void onDataChunk(List<T> chunk);

    void processComplete();

    void processError(Throwable e);
  }

  private static void HandlerFLux() {
    Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
            .handle((i, sink) -> {
              String letter = alphabet(i);
              if (letter != null)
                sink.next(letter);
            });
    printFlux(alphabet);
  }

  public static String alphabet(int letterNumber) {
    if (letterNumber < 1 || letterNumber > 26) {
      return null;
    }
    int letterIndexAscii = 'A' + letterNumber - 1;
    return "" + (char) letterIndexAscii;
  }
}
