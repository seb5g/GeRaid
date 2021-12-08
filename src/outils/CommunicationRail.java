/**
 * 
 */
package outils;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;


/**
 * <P>
 * Titre : CommunicationRail.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class CommunicationRail
{
  boolean autorisationCommunique = true;
  boolean communication;
 
  /**constructeur initialise le driver de la com et repertorie toutes les ports present sur l'ordinateur.
  ATTENTION si ce constructeur est appeller une deuxieme fois, les ports détecté s'ajouteront à ceux détéctés lors du premier appel.  
  */
  public CommunicationRail()
  {
    
  }
 
  /**repertorie les ports serie utilisable par le rail.
  @return un tableau de string de tout les noms des ports que peut utiliser le rail.
  */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public String[] getListePortRail(){
 
   ArrayList tableauPortId = new ArrayList();
   Enumeration listPort = CommPortIdentifier.getPortIdentifiers();
   while( listPort.hasMoreElements()){
    tableauPortId.add(listPort.nextElement());
   }
   int i;
 
   for (i = 0; i<tableauPortId.size(); i++){
    if ((((CommPortIdentifier)tableauPortId.get(i)).getPortType())!= CommPortIdentifier.PORT_SERIAL){
     tableauPortId.remove(i);
     i--;
    }
   }
   i = tableauPortId.size();
   String []listePortRail = new String[i];
   CommPortIdentifier portId;
   for (i = 0; i<tableauPortId.size(); i++){
    portId = (CommPortIdentifier)tableauPortId.get(i);
    listePortRail[i]= portId.getName();
   }
   return listePortRail;
 
  }
}
