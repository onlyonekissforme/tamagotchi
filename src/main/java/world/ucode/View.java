package world.ucode;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.SQLException;
import java.util.Random;

public class View {
  Scene sceneOver;
  Scene guess;
  Scene gMenu;
  Scene lod;
  Scene gamePlay;
  Scene newGam;
  boolean priznak = false;
  Controller control = new Controller();
  AnchorPane gameMenu = new AnchorPane();


  public void gameover(ModelDatabase data, Stage stage) {
    AnchorPane over = new AnchorPane();
    sceneOver = new Scene(over, 1100, 700);
    gMenu = new Scene(gameMenu, 1100, 700);
    over.setStyle("-fx-background-color: #000;");
    Image upgrave = new Image("/надгроби.png");
    ImageView upgr = new ImageView(upgrave);
    upgr.setLayoutX(200);
    upgr.setLayoutY(200);
    over.getChildren().add(upgr);
    Button menu = new Button("Перейти в меню");
    menu.setPrefSize(250.0, 80.0);
    menu.setStyle("-fx-background-color: #1eca2b; -fx-text-fill: #000; -fx-font-size: 25;");
    menu.setLayoutX(700);
    menu.setLayoutY(300);
    over.getChildren().add(menu);
    control.gameov(menu, data, stage, gMenu);
}
  public void gameGuess(Stage stage, Scene gamePlay, int happiness, ModelTimers tim) {
    AnchorPane an = new AnchorPane();
    guess = new Scene(an, 500, 300);
    Group grGuess = new Group();
    an.getChildren().add(grGuess);
    an.setStyle("-fx-background-color: #1eca2b;");
    Random rand = new Random(System.currentTimeMillis());
    int y = 1 + rand.nextInt(99);
    Label label = new Label("Я загадал число от 1 до 100. Угадай!");
    label.setTextFill(Color.rgb(200, 11, 221));
    label.setFont(Font.font("Aerial", FontWeight.BOLD, 20));
    label.setLayoutX(30);
    label.setLayoutY(30);
    grGuess.getChildren().add(label);
    TextField text2 = new TextField();
    text2.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #ffffff; -fx-font-size: 20;");
    text2.setLayoutX(30);
    text2.setLayoutY(70);
    text2.setPrefSize(450.0, 50.0);
    grGuess.getChildren().add(text2);
    Button choice = new Button("Выбрать");
    choice.setPrefSize(450.0, 50.0);
    choice.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 20;");
    choice.setLayoutX(30);
    choice.setLayoutY(140);
    grGuess.getChildren().add(choice);
    Button gou = new Button("Вернуться");
    gou.setPrefSize(450.0, 50.0);
    gou.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 20;");
    gou.setLayoutX(30);
    gou.setLayoutY(210);
    grGuess.getChildren().add(gou);
    priznak = control.guessGame(choice, text2, y, gou, stage, gamePlay, tim);
    if (priznak)
      happiness += 15;
  }

  public void newGame(ModelTimers tim, ModelDatabase data, Stage stage, String[] type) {
    AnchorPane NG = new AnchorPane();
    newGam = new Scene(NG, 1100, 700);
    Group grNG = new Group();
    NG.getChildren().add(grNG);
    Image imageBack = new Image("/meadow.jpg");
    ImageView imageBack2 = new ImageView(imageBack);
    imageBack2.setFitHeight(700);
    imageBack2.setFitWidth(1100);
    grNG.getChildren().addAll(imageBack2);
    Label labelc = new Label("Дай имя питомцу");
    labelc.setTextFill(Color.rgb(200, 11, 221));
    labelc.setFont(Font.font("Aerial", FontWeight.BOLD, 35));
    labelc.setLayoutX(100);
    labelc.setLayoutY(35);
    grNG.getChildren().add(labelc);
    MenuItem menuItem1 = new MenuItem("Цыпленок");
    menuItem1.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    MenuItem menuItem2 = new MenuItem("Голубь");
    menuItem2.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    MenuItem menuItem3 = new MenuItem("Панда");
    menuItem3.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    MenuButton menuButton = new MenuButton("Выбери питомца", null, menuItem1, menuItem2, menuItem3);
    menuButton.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    menuButton.setLayoutX(100);
    menuButton.setLayoutY(300);
    grNG.getChildren().add(menuButton);
    TextField text = new TextField();
    text.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #ffffff; -fx-font-size: 30;");
    text.setLayoutX(100);
    text.setLayoutY(100);
    text.setPrefSize(300.0, 80.0);
    grNG.getChildren().add(text);
    Button click = new Button("Выбрать");
    click.setPrefSize(300.0, 80.0);
    click.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    click.setLayoutX(100);
    click.setLayoutY(200);
    grNG.getChildren().add(click);
    try{
      data.conn();
      data.create();
    }
    catch(SQLException ex) {
      System.out.println("исключение базы данных");
    }
    catch (ClassNotFoundException cl) {
      System.out.println("не нашли класс");
    }
    click.setOnAction(value -> {
      tim.zver = text.getText();
    });
    Button change1 = new Button("Изменить цвет питомца");
    change1.setPrefSize(500.0, 80.0);
    change1.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    change1.setLayoutX(500);
    change1.setLayoutY(35);
    grNG.getChildren().add(change1);
    Button change2 = new Button("Изменить цвет клюва");
    change2.setPrefSize(500.0, 80.0);
    change2.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    change2.setLayoutX(500);
    change2.setLayoutY(135);
    grNG.getChildren().add(change2);
    Image[] pitom = new Image[6];
    for (int a = 0; a < 6; a++)
      pitom[a] = new Image("/" + tim.anim[a] + 13 + ".png");
    ImageView pitomets = new ImageView();
    Button shows = new Button("Показать");
    shows.setPrefSize(300.0, 80.0);
    shows.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    shows.setLayoutX(500);
    shows.setLayoutY(235);
    grNG.getChildren().add(shows);
    pitomets.setLayoutX(500);
    pitomets.setLayoutY(400);
    grNG.getChildren().add(pitomets);
    shows.setOnAction(value -> {
      pitomets.setImage(pitom[tim.animal - 1]);
    });
    Button plays = new Button("Играть");
    plays.setPrefSize(300.0, 80.0);
    plays.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 30;");
    plays.setLayoutX(500);
    plays.setLayoutY(335);
    grNG.getChildren().add(plays);
    if(tim.animal == 0)
      tim.animal = 1;
    tim.animals = null;
    tim.animals = tim.makeImage(tim.animal, tim.anim);
    control.newgame(menuItem1, menuItem2, menuItem3, tim, change1, change2, plays, stage, type, gamePlay, data);
  }

  public void gamePlay(ModelTimers tim, String[] type, Stage stage) {
    AnchorPane anchy = new AnchorPane();
    gamePlay = new Scene(anchy, 1100, 700);
    Group gr = new Group();
    anchy.getChildren().add(gr);
    Image image = new Image("/meadow.jpg");
    ImageView meadow = new ImageView(image);
    meadow.setFitHeight(700);
    meadow.setFitWidth(1100);
    gr.getChildren().addAll(meadow);
    tim.chicken.setImage(tim.animals[12]);
    tim.chicken.setX(100);
    tim.chicken.setY(450);
    gr.getChildren().add(tim.chicken);
    tim.nam.setText("имя " + tim.zver);
    tim.nam.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.nam.setFill(Color.rgb(115, 115, 115));
    tim.nam.setX(40);
    tim.nam.setY(50);
    gr.getChildren().add(tim.nam);
    tim.typ.setText("тип " + type[tim.animal]);
    tim.typ.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.typ.setFill(Color.rgb(0, 0, 0));
    tim.typ.setX(40);
    tim.typ.setY(100);
    gr.getChildren().add(tim.typ);
    tim.happinessy.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.happinessy.setFill(Color.rgb(200, 11, 221));
    tim.happinessy.setX(400);
    tim.happinessy.setY(50);
    gr.getChildren().add(tim.happinessy);
    tim.healthy.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.healthy.setFill(Color.rgb(30, 202, 43));
    tim.healthy.setX(400);
    tim.healthy.setY(110);
    gr.getChildren().add(tim.healthy);
    tim.hungery.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.hungery.setFill(Color.rgb(208, 23, 79));
    tim.hungery.setX(400);
    tim.hungery.setY(170);
    gr.getChildren().add(tim.hungery);
    tim.thirsty.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.thirsty.setFill(Color.rgb(39, 9, 132));
    tim.thirsty.setX(400);
    tim.thirsty.setY(230);
    gr.getChildren().add(tim.thirsty);
    tim.cleanlinessy.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    tim.cleanlinessy.setFill(Color.rgb(62, 62, 62));
    tim.cleanlinessy.setX(400);
    tim.cleanlinessy.setY(290);
    gr.getChildren().add(tim.cleanlinessy);
    Button medicine = new Button("Лечить");
    medicine.setPrefSize(200.0, 40.0);
    medicine.setStyle("-fx-background-color: #1eca2b; -fx-text-fill: #000; -fx-font-size: 25;");
    medicine.setLayoutX(800);
    medicine.setLayoutY(30);
    gr.getChildren().add(medicine);
    Button food = new Button("Кормить");
    food.setPrefSize(200.0, 40.0);
    food.setStyle("-fx-background-color: #d0174f; -fx-text-fill: #000; -fx-font-size: 25;");
    food.setLayoutX(800);
    food.setLayoutY(100);
    gr.getChildren().add(food);
    Button drink = new Button("Поить");
    drink.setPrefSize(200.0, 40.0);
    drink.setStyle("-fx-background-color: #270984; -fx-text-fill: #000; -fx-font-size: 25;");
    drink.setLayoutX(800);
    drink.setLayoutY(170);
    gr.getChildren().add(drink);
    Button clean = new Button("Уборка");
    clean.setPrefSize(200.0, 40.0);
    clean.setStyle("-fx-background-color: #3e3e3e; -fx-text-fill: #000; -fx-font-size: 25;");
    clean.setLayoutX(800);
    clean.setLayoutY(240);
    gr.getChildren().add(clean);
    Button dance = new Button("Танцуй");
    dance.setPrefSize(200.0, 40.0);
    dance.setStyle("-fx-background-color: #c80bdd; -fx-text-fill: #000; -fx-font-size: 25;");
    dance.setLayoutX(40);
    dance.setLayoutY(130);
    gr.getChildren().add(dance);
    Button game = new Button("Поиграем");
    game.setPrefSize(200.0, 40.0);
    game.setStyle("-fx-background-color: #c80bdd; -fx-text-fill: #000; -fx-font-size: 25;");
    game.setLayoutX(40);
    game.setLayoutY(200);
    gr.getChildren().add(game);
    gr.getChildren().add(tim.shit);
    tim.dance();
    tim.move();
    tim.tl.setCycleCount(2000000);
    tim.tl.setAutoReverse(true);
    tim.tl.play();
    tim.showIt(stage, sceneOver);
    tim.helt.setCycleCount(2000000);
    tim.helt.setAutoReverse(true);
    tim.helt.pause();
    stage.setTitle("Tamagotchi");
    stage.show();
    Path path = new Path();
    path.getElements().add(new MoveTo(100, 550));
    path.getElements().add(new LineTo(1000, 550));
    path.setStyle("visibility: hidden;");
    tim.ptr.setDuration(Duration.seconds(10));
    tim.ptr.setPath(path);
    tim.ptr.setNode((ImageView) tim.chicken);
    tim.ptr.setCycleCount(200000);
    tim.ptr.setAutoReverse(true);
    tim.ptr.play();
    gr.getChildren().add(path);
    gr.getChildren().add(tim.tabs);
    gr.getChildren().add(tim.wormy);
    gr.getChildren().add(tim.drinky);
    gr.getChildren().add(tim.scoopy);
    control.gamePlay(medicine, tim, food, gr, drink, dance, clean);
    game.setOnAction(value -> {
     tim.helt.pause();
     gameGuess(stage, gamePlay, tim.happiness, tim);
     stage.setScene(guess);
   });
  }

  public void loadGame (ModelTimers tim, String[] type, ModelDatabase data, Stage stage) {
    AnchorPane LG = new AnchorPane();
    lod = new Scene(LG, 1100, 700);
    Group loadGame = new Group();
    LG.getChildren().add(loadGame);
    Image im = new Image("/meadow.jpg");
    ImageView imv = new ImageView(im);
    imv.setFitHeight(700);
    imv.setFitWidth(1100);
    loadGame.getChildren().add(imv);
    Label lab = new Label("Давай оживим твоего питомца");
    lab.setTextFill(Color.rgb(200, 11, 221));
    lab.setFont(Font.font("Aerial", FontWeight.BOLD, 40));
    lab.setLayoutX(200);
    lab.setLayoutY(150);
    loadGame.getChildren().add(lab);
    Label lab2 = new Label("Введи имя своего питомца");
    lab2.setTextFill(Color.rgb(64, 0, 64));
    lab2.setFont(Font.font("Aerial", FontWeight.BOLD, 30));
    lab2.setLayoutX(350);
    lab2.setLayoutY(300);
    loadGame.getChildren().add(lab2);
    TextField tex = new TextField();
    tex.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #ffffff; -fx-font-size: 30;");
    tex.setLayoutX(300);
    tex.setLayoutY(400);
    tex.setPrefSize(450.0, 50.0);
    loadGame.getChildren().add(tex);
    Button come = new Button("Оживить");
    come.setPrefSize(450.0, 50.0);
    come.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #000; -fx-font-size: 20;");
    come.setLayoutX(300);
    come.setLayoutY(500);
    loadGame.getChildren().add(come);
    control.loadGame(come, tim, tex, stage, gamePlay, newGam, type, data);
  }

  public void gameMenu(Stage stage, Scene newGam, Scene lod) {
    Group grMenu = new Group();
    gameMenu.getChildren().add(grMenu);
    Image gImage = new Image("/meadow.jpg");
    ImageView gMImage = new ImageView(gImage);
    gMImage.setFitWidth(1100);
    gMImage.setFitHeight(700);
    grMenu.getChildren().add(gMImage);
    Label label = new Label("Давай вырaстим оцифрованного питомца");
    label.setTextFill(Color.rgb(200, 11, 221));
    label.setFont(Font.font("Aerial", FontWeight.BOLD, 40));
    label.setLayoutX(100);
    label.setLayoutY(100);
    grMenu.getChildren().add(label);
    Button newGame = new Button("Новая игра");
    newGame.setPrefSize(300.0, 80.0);
    newGame.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #ffffff; -fx-font-size: 30;");
    newGame.setLayoutX(375);
    newGame.setLayoutY(250);
    grMenu.getChildren().add(newGame);
    Button load = new Button("Загрузить игру");
    load.setPrefSize(300.0, 80.0);
    load.setStyle("-fx-background-color: #8000ff; -fx-text-fill: #ffffff; -fx-font-size: 30;");
    load.setLayoutX(375);
    load.setLayoutY(400);
    grMenu.getChildren().add(load);
    control.gameMenu(newGame, stage, newGam, load, lod);
  }
}