/**
 * Created by Hakan Tanis on 17.04.2018.
 */
public class Knoten {
    public static void main(String[] args) {
        var Knotenmenge;
        Knotenmenge=[];
        char [ABCDEFGHIJKLMNOPQRSTUVWXYZ] Knotenname = new char [26]; // Knotennamen gehen Default von A bis Z
        if NeuerKnotenname nicht benannt dann Knotenname=A if else Knotenname belegt dann Knotenname++;  // falls keine Knotennamen angegeben werden,
                                                                                                // werden die Knoten default von A bis Z benannt // Char array erstellen
         if Knoten benamen dann Knotenname=" new name"; // wenn der Nutzer den Knoten benennen möchte, wird der neue Name übernommen

        //Zufallsmatrix erstellen 
        Knotenmenge=[random];
         Knoten knoten[random] = new Knoten("[]");             // das Objekt des Knoten wird erzeugt 
        knoten[random].addDestination(knoten[], []); 
        
        //Kanntenmatrix
       Knoten knoten[A] = new Knoten("[]");             // das Objekt des Knoten wird erzeugt 
        knoten[].addDestination(knoten[], []);            // als erstes wird der Anfangsknoten eingegeben und dann der verbundene Knoten eingegeben und zu letzt die Kantenlänge
                                                            //z.B von Knoten A zu Knoten B ist die Länge 10 

        boolean [] []   zweidimArray = new boolean [26][12];//26 Zeilen und 12 Spalte



        //Array mit Werten füllen
        zweidimArray[0][0]=true; // Hardcode Anfangsknoten ist immer true (Knotenabschnitt)

        if new knoten verbunden mit alten Knoten dann altenKnoten.Spalte +1  // prüfen ob der neue Knoten der geprüft wird mit dem
                  wenn nicht dann wird der Knoten in Zeile+1 gepackt ;       // Anfangsknoten verbunden ist und falls ja dann wird dieser
                                                                            // in die nächste Spalte in Zeile 1 eingetragen und wenn nicht dann wird
                                                                            // der Knoten in der nächsten Zeile eingefügt

        if nächster Knoten=Spalte vom letzten Knoten dann Zeile+1; // ohne zwei Knoten in eine Zelle zupacken
        if Knoten=behandelt dann wähle nächsten Knoten;


        zweidimArray[0][1]=false;
        zweidimArray[1][0]=false;
        zweidimArray[1][1]=true;
        zweidimArray[1][2]=true;
        zweidimArray[2][0]=false;
        zweidimArray[2][2]=true;
        zweidimArray[2][3]=true;
        zweidimArray[2][8]=false;


        //Werte aus dem Array lesen
        System.out.println(zweidimArray[0][0]);
        System.out.println(zweidimArray[0][1]);
        System.out.println(zweidimArray[1][0]);
        System.out.println(zweidimArray[1][1]);
        System.out.println(zweidimArray[1][2]);
        System.out.println(zweidimArray[2][0]);
        System.out.println(zweidimArray[2][1]);
        System.out.println(zweidimArray[2][2]);
        System.out.println(zweidimArray[2][3]);
        System.out.println(zweidimArray[2][8]);

    }
}

       // Graphen erstellen 
public class Graph  {
    private Set<Knoten> knoten = new HashSet<>(); // HashSet ist eine Mengenklasse die die Knoten
     
    public void addKnoten(Knoten Knoten[]) {
        knoten.add(knoten[]);
    }
}





// Kantenabschnitt wird hier beantwortet
Kante.farbe.default=black; //die Grundfarbe der Kanten ist schwarz
Kante.farbe.Dijkstra= blau; // wenn der Dijkstra durchlauft färben sich die aktuell bearbeiteten Kanten zu blau
Kante.farbe.bearbeitet= grau; // wenn der Dijkstra die Kante fertig bearbeitet hat, färbt sich die Kante blau
Kante.farbe.auswaehlen= gelb; // beim Selbsttest färben sich die angeklickten Kanten gelb
Kante.farbe.falsche= rot; // wenn die ausgewaehlte Kante falsch war, färbt sich die Kante rot
Kante.farbe.richtig= grün; //  wenn die ausgewählte Kante richtig war, färbt sich die Kante grün

