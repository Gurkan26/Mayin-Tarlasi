



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Zor implements  ActionListener{
      String ad;
    JMenuBar menubar = new JMenuBar();
     JMenu iletisim =new JMenu("İletişim");
     JMenuItem kimim = new JMenuItem("Gürkan Şentürk --Tıkla--");
    JMenu oyun = new JMenu("Oyun");
    JMenuItem baslangıc = new JMenuItem("Başlangıç");
    JMenuItem orta = new JMenuItem("Orta");
    JMenuItem zor = new JMenuItem("Zor");
    JMenuItem ozel = new JMenuItem("Özel Oyun");
    JFrame frame = new JFrame("Mayın Tarlası");
    ImageIcon ikon = new ImageIcon(getClass().getResource("zadrot45.png"));
    JButton reset = new JButton(ikon);
    JPanel panel = new JPanel();
     JMenuItem puantablosu = new JMenuItem("Puan Tablosu");
     JLabel zaman = new JLabel();
     JLabel puan = new JLabel();
       JLabel ara1 = new JLabel();
    JLabel ara2 = new JLabel();
    JLabel ara3 = new JLabel();
    JLabel ara4 = new JLabel();
    int süre =0;
    JButton[][] butonlar = new JButton[25][25];
        int[][] sayımlar = new int[25][25];
        
    Container grid = new Container();// düzen için
    final int mayın = 10;
    int sayac=0;
    int puansayısı=0;
    public Zor() {
     
   puan.setFont(new Font("Comic Sans Ms", Font.BOLD, 16));
        frame.setSize(1050, 900);
        frame.setLayout(new BorderLayout());
      zaman.setPreferredSize(new Dimension(30,30));
    reset.setPreferredSize(new Dimension(40,40));
        puan.setPreferredSize(new Dimension(30,30));
        ara1.setPreferredSize(new Dimension(200,30));
                ara2.setPreferredSize(new Dimension(200,30));
        ara3.setPreferredSize(new Dimension(200,30));
        ara4.setPreferredSize(new Dimension(200,30));
        frame.setJMenuBar(menubar);
         frame.add(panel,BorderLayout.NORTH);
    
         
         menubar.add(oyun);
        panel.add(puan);
        panel.add(ara1);
        panel.add(ara2);
       panel.add(reset);
        panel.add(ara3);
       panel.add(ara4);
       panel.add(zaman);
        oyun.add(baslangıc);
        oyun.add(orta);
        oyun.add(zor);
        oyun.add(ozel);
      oyun.add(puantablosu);
                 
       menubar.add(iletisim);
       iletisim.add(kimim);
      puantablosu.addActionListener(this);
baslangıc.addActionListener(this);
orta.addActionListener(this);

zor.addActionListener(this);
kimim.addActionListener(this);

ozel.addActionListener(this);
reset.addActionListener(this);

       mayinyaratici();
        frame.add(grid, BorderLayout.CENTER);
          
     grid.setLayout(new GridLayout(25, 25));
        for (int i = 0; i < butonlar.length; i++) {
            for (int j = 0; j < butonlar.length; j++) {
                butonlar[i][j] = new JButton();
                butonlar[i][j].addActionListener(this);
                grid.add(butonlar[i][j]);

            }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
           frame.setLocationRelativeTo(null);
    }
 
    
    
    
    public void mayinyaratici() {// Matrisi başlat
         
       
     ArrayList<Integer> list = new ArrayList<Integer>();
        for (int x = 0; x < sayımlar.length; x++) {
            for (int y = 0; y < sayımlar[0].length; y++) {
                list.add(x * 100 + y);
            }
        }

        sayımlar = new int[25][25];
        //MAYIN KISMI
        for (int i = 0; i < 99; i++) {
            int secim = (int) (Math.random() * list.size());
            sayımlar[list.get(secim) / 100][list.get(secim) % 100] = mayın;
            list.remove(secim);// böylece seçimi arayüzde kaldırdık

        }
        //BURADA DA SEÇİLEN BUTONUN ETRAFINI TARAYACAĞIz
//----------------
        for (int x = 0; x < sayımlar.length; x++) {
            for (int y = 0; y < sayımlar[0].length; y++) {
                if (sayımlar[x][y] != mayın) {

                    int komsusayisi = 0;

                    if (x > 0 && y > 0 && sayımlar[x - 1][y - 1] == mayın) {/* SEÇİLİ SÜTÜN SOL ÜSTÜ*/

                        komsusayisi++;

                    }

                    if (y > 0 && sayımlar[x][y - 1] == mayın) {// ÜSTÜ
                        komsusayisi++;

                    }

                    if (x < sayımlar.length - 1 && y > 0 && sayımlar[x + 1][y - 1] == mayın) {//AŞAĞI SAĞI
                        komsusayisi++;

                    }
                    if (x > 0 && sayımlar[x - 1][y] == mayın) {
                        komsusayisi++;

                    }
                    if (x < sayımlar.length - 1 && sayımlar[x + 1][y] == mayın) {
                        komsusayisi++;

                    }
                    if (x > 0 && y < sayımlar[0].length - 1 && sayımlar[x - 1][y + 1] == mayın) {
                        komsusayisi++;

                    }
                    if (y < sayımlar[0].length - 1 && sayımlar[x][y + 1] == mayın) {
                        komsusayisi++;

                    }
                    if (x < sayımlar.length - 1 && y < sayımlar[0].length - 1 && sayımlar[x + 1][y + 1] == mayın) {//BURA BAK
                        komsusayisi++;

                    }

                    sayımlar[x][y] = komsusayisi;

                }

            }
        }
    }
    
    
    
    public void kazankontrol() {
        boolean kazandım = true;
        for (int x = 0; x < sayımlar.length; x++) {
            for (int y = 0; y < sayımlar[0].length; y++) {
                if (sayımlar[x][y] != mayın && butonlar[x][y].isEnabled() == true) {
                    kazandım = false;
                }

            }

        }
        if (kazandım == true) {
            alkisekle();
            JOptionPane.showMessageDialog(frame, "Kazandın");
            ad=JOptionPane.showInputDialog("Lütfen adını gir ve Tabloda yer al! ");
           verialıcı vr= new verialıcı();
           vr.setAd(ad);
           int puann = Integer.valueOf(puan.getText());
           vr.setPuan(puann);
           vr.setSüre(süre);
            

        }
    }
    public void sıfırlarıtemizle(ArrayList<Integer> temizle) {//BUTONA BASILDIĞI ZAMAN ETRAFINDA 0 VAR İSE ONLAR AÇILACAK.
        if (temizle.size() == 0) {
            return;
        } else {

            int x = temizle.get(0) / 100;

            int y = temizle.get(0) % 100;

            temizle.remove(0);

            /*BURADAN AŞAĞISI SOL VE SAĞ ÜST ÇAPRAZ KONTROLÜ-----------------------------------------------------*/
            if (x > 0 && y > 0 && butonlar[x - 1][y - 1].isEnabled())// sol üste baktık
            {

                butonlar[x - 1][y - 1].setText(sayımlar[x - 1][y - 1] + "");

                butonlar[x - 1][y - 1].setEnabled(false);

                if (sayımlar[x - 1][y - 1] == 0) {

                    temizle.add((x - 1) * 100 + (y - 1));

                }
                  puansayısı=puansayısı+Integer.valueOf(butonlar[x-1][y-1].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
            }
            if (y > 0 && butonlar[x][y - 1].isEnabled())//direk üste baktık
            {
                butonlar[x][y - 1].setText(sayımlar[x][y - 1] + "");
                butonlar[x][y - 1].setEnabled(false);
                if (sayımlar[x][y - 1] == 0) {
                    temizle.add(x * 100 + (y - 1));
                }

         puansayısı=puansayısı+Integer.valueOf(butonlar[x][y-1].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
            }

            if (x < sayımlar.length - 1 && y > 0 && butonlar[x + 1][y - 1].isEnabled())// sağ üst
            {

                butonlar[x + 1][y - 1].setText(sayımlar[x + 1][y - 1] + "");
           
 
                butonlar[x + 1][y - 1].setEnabled(false);
                if (sayımlar[x + 1][y - 1] == 0) {
                    temizle.add((x + 1) * 100 + (y - 1));
                }

         puansayısı=puansayısı+Integer.valueOf(butonlar[x+1][y-1].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
            }
            /*BURADAN AŞAĞISI SOL VE SAĞ KONTORLÜ------------------------------------------------------*/
            if (x > 0 && butonlar[x - 1][y].isEnabled())// sol 
            {

                butonlar[x - 1][y].setText(sayımlar[x - 1][y] + "");

                butonlar[x - 1][y].setEnabled(false);

                if (sayımlar[x - 1][y] == 0) {

                    temizle.add((x - 1) * 100 + y);

                }

         puansayısı=puansayısı+Integer.valueOf(butonlar[x-1][y].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
            }

            if (x < sayımlar.length - 1 && butonlar[x + 1][y].isEnabled())//sağ
            {

                butonlar[x + 1][y].setText(sayımlar[x + 1][y] + "");

                butonlar[x + 1][y].setEnabled(false);
                if (sayımlar[x + 1][y] == 0) {
                    temizle.add((x + 1) * 100 + y);
                }
  puansayısı=puansayısı+Integer.valueOf(butonlar[x+1][y].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
            }
            /*BURADAN AŞAĞISI DA SOL VE SAĞ ALT ÇAPRAZLAR--------------------------------------------------*/
            if (x > 0 && y < sayımlar[0].length - 1 && butonlar[x - 1][y + 1].isEnabled())// sol ALT
            {
                butonlar[x - 1][y + 1].setText(sayımlar[x - 1][y + 1] + "");
                butonlar[x - 1][y + 1].setEnabled(false);
                if (sayımlar[x - 1][y + 1] == 0) {
                    temizle.add((x - 1) * 100 + (y + 1));
                }
                  puansayısı=puansayısı+Integer.valueOf(butonlar[x-1][y+1].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
            }
            if (y < sayımlar[0].length - 1 && butonlar[x][y + 1].isEnabled())//direk ALT
            {
                butonlar[x][y + 1].setText(sayımlar[x][y + 1] + "");

                butonlar[x][y + 1].setEnabled(false);
                if (sayımlar[x][y + 1] == 0) {
                    temizle.add(x * 100 + (y + 1));
                }
                  puansayısı=puansayısı+Integer.valueOf(butonlar[x][y+1].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);

            }
            if (x < sayımlar.length - 1 && y < sayımlar[0].length - 1 && butonlar[x + 1][y + 1].isEnabled())// sağ ALT
            {

                butonlar[x + 1][y + 1].setText(sayımlar[x + 1][y + 1] + "");

                butonlar[x + 1][y + 1].setEnabled(false);
                if (sayımlar[x + 1][y + 1] == 0) {
                    temizle.add((x + 1) * 100 + (y + 1));
                }
                  puansayısı=puansayısı+Integer.valueOf(butonlar[x+1][y+1].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);

            }
            sıfırlarıtemizle(temizle);
        }

    }
    public void bombasesi()
    {
    
        try {
            AudioInputStream stream= AudioSystem.getAudioInputStream(new File("patlamayeni2.wav"));
            Clip klip =AudioSystem.getClip();
            klip.open(stream);
            klip.start();
                    } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void alkisekle()
    {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("alkıs2.wav"));
        
            Clip klip =AudioSystem.getClip();
            klip.open(stream);
            klip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
      public void oyunbitti()//EĞER MAYINA BASILIRSA OYUN BİTECEK VE KELİN BAŞI GÖZÜKECEK
    {
        for (int i = 0; i < butonlar.length; i++) {
            for (int j = 0; j < butonlar[0].length; j++) {
                if (butonlar[i][j].isEnabled()) {
                    if (sayımlar[i][j] != mayın) {
                        butonlar[i][j].setText(sayımlar[i][j] + "");
                        butonlar[i][j].setEnabled(false);

                    } else {
 ImageIcon mayınicon = new ImageIcon(getClass().getResource("mayınminnak.png"));
                        butonlar[i][j].setIcon(mayınicon);
                        bombasesi();
                        //butonlar[i][j].setEnabled(false);
                    }
                }
            }
        }

    }

       Timer timer = new Timer();
      TimerTask gorev = new TimerTask() {

           @Override
           public void run() {
         
           süre++;
          String süree = String.valueOf(süre);
           zaman.setText(süree);
           
           }
       };
      
      
      
      
      
      
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==puantablosu)
         {
         eniyiler ei = new eniyiler();
         frame.setVisible(false);
         ei.setVisible(true);
         }
        if(e.getSource()==orta){
           frame.setVisible(false);
           Orta ort=new Orta();
              ort.mayinyaratici();
    
        }
        if(e.getSource()==kimim){
           try {
               Desktop.getDesktop().browse(new URI("www.gurkansenturk.com"));
           } catch (URISyntaxException ex) {
               Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(Zor.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       if(e.getSource()==baslangıc){
           frame.setVisible(false);
          MayınTarlasi_2 myt2 = new MayınTarlasi_2();
          myt2.mayinyaratici();
       
       }
    
    if (e.getSource().equals(reset)) {// OYUNU SIFIRLA
           Zor zr = new Zor();
          
           zr.mayinyaratici();
            frame.setVisible(false);
             timer.cancel();
        } else {
            for (int i = 0; i < butonlar.length; i++) {
                for (int j = 0; j < butonlar[0].length; j++) {
                    if (e.getSource().equals(butonlar[i][j])) {
                        if (sayımlar[i][j] == mayın) {
                            ImageIcon mayınicon = new ImageIcon(getClass().getResource("mayınminnak.png"));
                        butonlar[i][j].setIcon(mayınicon);
                         timer.cancel();
                            oyunbitti();
                        } else if (sayımlar[i][j] == 0) {
                            butonlar[i][j].setText(sayımlar[i][j] + "");
                            butonlar[i][j].setEnabled(false);
                            ArrayList<Integer> temizle = new ArrayList<Integer>();
                            temizle.add(i * 100 + j);
                            puansayısı=puansayısı+Integer.valueOf(butonlar[i][j].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
                            sıfırlarıtemizle(temizle);
                            kazankontrol();
                        } else {
                            butonlar[i][j].setText(sayımlar[i][j] + "");
                            puansayısı=puansayısı+Integer.valueOf(butonlar[i][j].getText());
               String puanstring=String.valueOf(puansayısı);
               puan.setText(puanstring);
                            butonlar[i][j].setEnabled(false);
                            kazankontrol();
                        }
                    }
                }
            }
            timer.schedule(gorev, 0, 1000);
        }
    
    }
    
    
    
    
    
    
    
    
}
