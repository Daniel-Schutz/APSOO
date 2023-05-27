package utils;

public class NovaExcecao extends Exception {
  private String message;

  public NovaExcecao(String message){
    this.message = message;
  }

  public String getMessage(){
    return this.message;
  }
}
