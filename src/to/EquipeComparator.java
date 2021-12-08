package to;

import java.util.Comparator;

public class EquipeComparator implements Comparator<Equipe>
{
  public static final int COMPARE_BY_DOSSARD = 0;
  public static final int COMPARE_BY_NOMEQUIPE = 1;
  public static final int COMPARE_BY_PUCE = 2;
  public static final int COMPARE_BY_CATEGORIE = 3;
  
  private TypeTri compare_mode = TypeTri.DOSSARD;
  
  public EquipeComparator(TypeTri compare_mode)
  {
    this.compare_mode = compare_mode;
  }
  
  @Override
  public int compare(Equipe o1, Equipe o2)
  {
    switch (compare_mode)
    {
      case DOSSARD:
        if(o1.getDossard().compareTo(o2.getDossard())>0)
        {
          return 1;
        }
        else
        {
          return -1;
        }
        
        case NOM:
          if(o1.getNom().compareTo(o2.getNom())>0)
          {
            return 1;
          }
          else
          {
            return -1;
          }
          
          case CATEGORIE:
            if(o1.getCategorie().getNomCourt().compareTo(o2.getCategorie().getNomCourt())>=0)
            {
              return 1;
            }
            else
            {
              return -1;
            }
            
            case PUCE:
              if(o1.getIdPuce().compareTo(o2.getIdPuce())>0)
              {
                return 1;
              }
              else
              {
                return -1;
              }

      default:
        return 0;
    }
  }

}
