package utils;

public class NovaExcecao extends Exception {
  private static String message;

  public NovaExcecao(String message){
    NovaExcecao.message = message;
  }

  public static String getNewMessage(){
    return message;
  }
}
