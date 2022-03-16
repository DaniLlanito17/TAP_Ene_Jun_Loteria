package sample.views;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Loteria extends Stage implements EventHandler{

    private VBox vBox;
    private HBox hBox1, hBox2, pantalla;
    private Button btnAtras, btnSiguiente, btnJugar;
    private Label lblTiempo, lblCartaFaltante;
    private GridPane gdpPlantilla;
    private Image imgCartaPlan, imgCarta, imgB;
    private ImageView imv, imvJ, imvB;
    private Scene escena;
    private int cont = 0, n, CartaFaltante = 16, control, vaux;
    private String [] arImagenes = {"arbol.jpg","bandera.jpg","barril.jpg","bota.jpg","botella.jpg","calavera.jpg","campana.jpg","cantarito.jpg","catring.jpg","chalupa.jpg",
            "corazon.jpg","corona.jpg","cotorro.jpg","dama.jpg","escalera.jpg","garza.jpg","maceta.jpg","mano.jpg","melon.jpg","muerte.jpg",
            "muerte.jpg","pajaro.jpg","palma.jpg","paraguas.jpg","pera.jpg","pino.jpg","rosa.jpg","sirena.jpg","sol,jpg","soldado.jpg",
            "venado.jpg"
    };
    private String [][] arImagenes2 = {
            //Carta1
            {"arbol.jpg","bandera.jpg","barril.jpg","bota.jpg","botella.jpg","calavera.jpg","campana.jpg","cantarito.jpg",
                    "catrin.jpg","dama.jpg","corazon.jpg","corona.jpg","cotorro.jpg","escalera.jpg","garza.jpg","venado.jpg"},
            //Carta 2
            {"garza.jpg","maceta","mano.jpg","melon.jpg","muerte.jpg","pajaro.jpg","palma.jpg","paraguas.jpg",
                    "pera.jpg","pino.jpg","rosa.jpg","sirena.jpg","sol.jpg","soldado.jpg","venado.jpg","arbol"},
            //Carta 3
            {"arbol.jpg","venado.jpg","soldado.jpg","barril.jpg","bota.jpg","sol.jpg","sirena.jpg","botella.jpg",
                    "calavera.jpg","rosa.jpg","pino.jpg","campana.jpg","cantarito.jpg","pera.jpg","paraguas.jpg","escalera.jpg"},
            //Carta 4
            {"catrin.jpg","dama.jpg","palma.jpg","pajaro.jpg","chalupa.jpg","corazon.jpg","muerte.jpg","melon.jpg",
                    "corona.jpg","cotorro.jpg","mano.jpg","maceta.jpg","escalera.jpg","garza.jpg","campana.jpg","arbol.jpg"},
            //Carta 5
            {"campana.jpg","cantarito.jpg","corazon.jpg","corona.jpg","escalera.jpg","maceta.jpg","mano.jpg","melon.jpg",
                    "pino.jpg","muerte.jpg","pajaro.jpg","sirena.jpg","sol.jpg","botella.jpg","bota.jpg","calaveraa.jpg"}
    };
    private Button[][] arBtnPlantilla = new Button[4][4];
    private Button btnCarta;
    private int []cartas = new int[arImagenes.length];

    public Loteria(){
        CrearIU();
        this.setTitle("Loteria c:");
        this.setScene(escena);
        this.show();
    }

    private void CrearIU(){
        //Cambiar de plantilla.
        btnAtras = new Button("Anterior");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cont>0){
                    cont--;
                    CrearPlantillas(cont);
                }
                else{
                    cont=4;
                    CrearPlantillas(cont);
                }
            }
        });
        btnAtras.setPrefWidth(100);
        btnSiguiente = new Button("Siguiente");
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(cont<4){
                    cont++;
                    CrearPlantillas(cont);
                }
                else{
                    cont=0;
                    CrearPlantillas(cont);
                }
                System.out.println("No. Plantilla: "+cont);
            }
        });
        btnSiguiente.setPrefWidth(100);
        lblCartaFaltante= new Label();
        String cartaF = "";
        cartaF = cartaF + CartaFaltante;
        lblCartaFaltante.setText(cartaF);
        hBox1 = new HBox();
        hBox1.setSpacing(5);
        hBox1.getChildren().addAll(btnAtras,btnSiguiente,lblCartaFaltante);

        gdpPlantilla = new GridPane();
        PlanInicio();
        hBox2 = new HBox();
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(gdpPlantilla);

        pantalla = new HBox();
        btnCarta= new Button();
        pantalla.getChildren().add(btnCarta);

        btnJugar = new Button("Jugar");
        btnJugar.setPrefWidth(350);
        btnJugar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);

        arBtnPlantilla[0][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,0);
            }
        });
        arBtnPlantilla[0][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,1);
            }
        });
        arBtnPlantilla[0][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,2);
            }
        });
        arBtnPlantilla[0][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,0,3);
            }
        });
        arBtnPlantilla[1][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,0);
            }
        });
        arBtnPlantilla[1][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,1);
            }
        });
        arBtnPlantilla[1][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,2);
            }
        });
        arBtnPlantilla[1][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,1,3);
            }
        });
        arBtnPlantilla[2][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,0);
            }
        });
        arBtnPlantilla[2][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,1);
            }
        });
        arBtnPlantilla[2][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,2);
            }
        });
        arBtnPlantilla[2][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,2,3);
            }
        });
        arBtnPlantilla[3][0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,0);
            }
        });
        arBtnPlantilla[3][1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,1);
            }
        });
        arBtnPlantilla[3][2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,2);
            }
        });
        arBtnPlantilla[3][3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control(n,3,3);
            }
        });
        vBox = new VBox();
        vBox.getChildren().addAll(hBox1,hBox2,btnJugar,pantalla);

        escena = new Scene(vBox,550,400);
    }


    private void PlanInicio() {
        int carta=0;
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                arBtnPlantilla[i][j] = new Button();
                imgCartaPlan = new Image("sample/images/"+arImagenes2[0][carta]);
                imv = new ImageView(imgCartaPlan);
                imv.setFitHeight(100);
                imv.setFitWidth(70);
                arBtnPlantilla[i][j].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[i][j],j,i);
                carta++;
            }
        }
    }

    private void control(int n,int renglon, int columna){
        int aux=0;
        System.out.println("Plantilla" + cont);
        for(int i=0;i<arBtnPlantilla.length;i++){
            for(int j=0;j<arBtnPlantilla[0].length;j++){
                System.out.println(arImagenes[n]+arImagenes2[cont][aux]);
                if(i==renglon && j==columna){
                    if(arImagenes[n].equalsIgnoreCase(arImagenes2[cont][aux]))
                    System.out.println(arImagenes[n]+""+arImagenes2[cont][aux]);
                    CartaFaltante--;
                    String cartaF="";
                    cartaF=cartaF+CartaFaltante;
                    lblCartaFaltante.setText(cartaF);
                    BorraCarta(i,j);
                    NuevaTarjeta();
                }
                aux++;
                System.out.println("Aux: " + aux);
            }
        }
    }

    private void BorraCarta(int cont, int aux) {
        imgB = new Image("sample/images/Voltear.jpg");
        imvB = new ImageView(imgB);
        imvB.setFitHeight(90);
        imvB.setFitWidth(60);
        arBtnPlantilla[cont][aux].setGraphic(imvB);
    }

    private void CrearPlantillas(int pos) {
        int carta = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                imgCartaPlan = new Image("sample/images/" + arImagenes2[pos][carta]);
                imv = new ImageView(imgCartaPlan);
                imv.setFitHeight(90);
                imv.setFitWidth(60);
                arBtnPlantilla[j][i].setGraphic(imv);
                carta++;
            }
        }
    }

    @Override
    public void handle(Event event) {
        System.out.println("Mi primer evento");
        Timer time =new Timer();
        Tiempo(time);
        NuevaTarjeta();
        Timeline transicion = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NuevaTarjeta();
            }
        }));
        transicion.setCycleCount(Timeline.INDEFINITE);
        transicion.play();
        if(CartaFaltante==0 || vaux == 25){
            transicion.stop();
        }
    }

    private boolean Tiempo(Timer time){
        time.scheduleAtFixedRate(new TimerTask() {
            int j=100;
            public void run() {
                j--;
                if(j==1){
                    System.out.println("FIN");
                }
            }
        },0,100);
        return  false;
    }

    private void NuevaTarjeta() {
        boolean opc;
        do{
            n=(int)(Math.random()*31);
            System.out.println(n);
            opc = verificar(cartas,n,control);
            if(n==0){
                control++;
            }
            if (opc==true){
                cartas[vaux]=n;
                vaux++;
                System.out.println("Aleatorio :"+n);
                imgCarta = new Image("sample/images/"+arImagenes[n]);
                imvJ = new ImageView(imgCarta);
                imvJ.setFitWidth(70);
                imvJ.setFitHeight(80);
                btnCarta.setGraphic(imvJ);
            }
        }while(opc!=true);
        imprimirV(cartas);
    }

    private void imprimirV(int[] cartas) {
        for(int i=0;i<cartas.length;i++){
            System.out.println(cartas[i]);
        }
    }

    private boolean verificar(int[] cartas, int n, int control) {
        for(int i=0;i<cartas.length;i++){
            System.out.println(cartas[i]+" n "+ n);
            if(n==0 && control==0 ){
                control++;
                return true;
            }
            if(cartas[i]==n){
                return false;
            }
        }
        return true;
    }
}