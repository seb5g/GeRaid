package to;

public class Categorie
{
  private String nomLong = "";
  private String nomCourt = "";
  
  public Categorie(String nomLong, String nomCourt)
  {
    this.nomLong = nomLong;
    this.nomCourt = nomCourt;
  }
  /**
   * @return the nomLong
   */
  public String getNomLong()
  {
    return nomLong;
  }
  /**
   * @param nomLong the nomLong to set
   */
  public void setNomLong(String nomLong)
  {
    this.nomLong = nomLong;
  }
  /**
   * @return the nomCourt
   */
  public String getNomCourt()
  {
    return nomCourt;
  }
  /**
   * @param nomCourt the nomCourt to set
   */
  public void setNomCourt(String nomCourt)
  {
    this.nomCourt = nomCourt;
  }
  
  public String toString()
  {
    return nomLong;
  }
}
