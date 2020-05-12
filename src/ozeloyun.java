


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public final class ozeloyun implements ActionListener{
      public int mayinsayisi=15;
  public int satir=10;
public int sutün=10;

    

    public void setSutün(int sutün) {
        this.sutün = sutün;
    }  
    JMenuBar menubar = new JMenuBar();
    JMenu oyun = new JMenu("Oyun");
    JMenuItem baslangıc = new JMenuItem("Başlangıç");
    JMenuItem orta = new JMenuItem("Orta");
    JMenuItem zor = new JMenuItem("Zor");
    JMenuItem ozel = new JMenuItem("Özel Oyun");
    JFrame frame = new JFrame("Mayın Tarlası");
     ImageIcon ikon = new ImageIcon(getClass().getResource("zadrot45.png"));
    JButton reset = new JButton(ikon);
    JPanel panel = new JPanel();
    JButton[][] butonlar = new JButton[satir][sutün];
    int[][] sayımlar = new int[satir][sutün];
    Container grid = new Container();// düzen için
    final int mayın = 10;

    public ozeloyun(int mayinsayisi, int satir, int sutün) {
        this.mayinsayisi = mayinsayisi;
        this.satir = satir;
        this.sutün = sutün;
    JOptionPane.showMessageDialog(frame, ""+satir+" "+sutün+" "+mayinsayisi);
     frame.setSize(1100, 900);
        frame.setLayout(new BorderLayout());
      reset.setPreferredSize(new Dimension(40,40));
        frame.setJMenuBar(menubar);
         frame.add(panel,BorderLayout.NORTH);
        menubar.add(oyun);
         panel.add(reset);
        oyun.add(baslangıc);
        oyun.add(orta);
        oyun.add(zor);
        oyun.add(ozel);
      //  frame.add(reset, BorderLayout.NORTH);
baslangıc.addActionListener(this);
orta.addActionListener(this);

zor.addActionListener(this);

ozel.addActionListener(this);
reset.addActionListener(this);

        mayinyaratici(satir,sutün,mayinsayisi);
        frame.add(grid, BorderLayout.CENTER);
          
     grid.setLayout(new GridLayout(satir,sutün));
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
 
   
 
    
    
    
    public void mayinyaratici(int satirr,int sutun,int mayinn) {// Matrisi başlat
    
       
     ArrayList<Integer> list = new ArrayList<Integer>();
        for (int x = 0; x < sayımlar.length; x++) {
            for (int y = 0; y < sayımlar[0].length; y++) {
                list.add(x * 100 + y);
            }
        }

        sayımlar = new int[satirr][sutun];
        //MAYIN KISMI
        for (int i = 0; i < mayinn; i++) {
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
            JOptionPane.showMessageDialog(frame, "Kazandın");

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
            }
            if (y > 0 && butonlar[x][y - 1].isEnabled())//direk üste baktık
            {
                butonlar[x][y - 1].setText(sayımlar[x][y - 1] + "");

                butonlar[x][y - 1].setEnabled(false);
                if (sayımlar[x][y - 1] == 0) {
                    temizle.add(x * 100 + (y - 1));
                }

            }

            if (x < sayımlar.length - 1 && y > 0 && butonlar[x + 1][y - 1].isEnabled())// sağ üst
            {

                butonlar[x + 1][y - 1].setText(sayımlar[x + 1][y - 1] + "");

                butonlar[x + 1][y - 1].setEnabled(false);
                if (sayımlar[x + 1][y - 1] == 0) {
                    temizle.add((x + 1) * 100 + (y - 1));
                }

            }
            /*BURADAN AŞAĞISI SOL VE SAĞ KONTORLÜ------------------------------------------------------*/
            if (x > 0 && butonlar[x - 1][y].isEnabled())// sol 
            {

                butonlar[x - 1][y].setText(sayımlar[x - 1][y] + "");

                butonlar[x - 1][y].setEnabled(false);

                if (sayımlar[x - 1][y] == 0) {

                    temizle.add((x - 1) * 100 + y);

                }

            }

            if (x < sayımlar.length - 1 && butonlar[x + 1][y].isEnabled())//sağ
            {

                butonlar[x + 1][y].setText(sayımlar[x + 1][y] + "");

                butonlar[x + 1][y].setEnabled(false);
                if (sayımlar[x + 1][y] == 0) {
                    temizle.add((x + 1) * 100 + y);
                }

            }
            /*BURADAN AŞAĞISI DA SOL VE SAĞ ALT ÇAPRAZLAR--------------------------------------------------*/
            if (x > 0 && y < sayımlar[0].length - 1 && butonlar[x - 1][y + 1].isEnabled())// sol ALT
            {
                butonlar[x - 1][y + 1].setText(sayımlar[x - 1][y + 1] + "");
                butonlar[x - 1][y + 1].setEnabled(false);
                if (sayımlar[x - 1][y + 1] == 0) {
                    temizle.add((x - 1) * 100 + (y + 1));
                }
            }
            if (y < sayımlar[0].length - 1 && butonlar[x][y + 1].isEnabled())//direk ALT
            {
                butonlar[x][y + 1].setText(sayımlar[x][y + 1] + "");

                butonlar[x][y + 1].setEnabled(false);
                if (sayımlar[x][y + 1] == 0) {
                    temizle.add(x * 100 + (y + 1));
                }

            }
            if (x < sayımlar.length - 1 && y < sayımlar[0].length - 1 && butonlar[x + 1][y + 1].isEnabled())// sağ ALT
            {

                butonlar[x + 1][y + 1].setText(sayımlar[x + 1][y + 1] + "");

                butonlar[x + 1][y + 1].setEnabled(false);
                if (sayımlar[x + 1][y + 1] == 0) {
                    temizle.add((x + 1) * 100 + (y + 1));
                }

            }
            sıfırlarıtemizle(temizle);
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
                       // butonlar[i][j].setEnabled(false);
                    }
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==orta){
           frame.setVisible(false);
           Orta ort=new Orta();
              ort.mayinyaratici();
    
        }
       if(e.getSource()==baslangıc){
           frame.setVisible(false);
          MayınTarlasi_2 myt2 = new MayınTarlasi_2();
          myt2.mayinyaratici();
       
       }
    
    if (e.getSource().equals(reset)) {// OYUNU SIFIRLA
            for (int x = 0; x < butonlar.length; x++) {
                for (int y = 0; y < butonlar[0].length; y++) {
                    butonlar[x][y].setEnabled(true);
                    butonlar[x][y].setText("");
                }

            }
           mayinyaratici(satir,sutün,mayinsayisi);
        } else {
            for (int i = 0; i < butonlar.length; i++) {
                for (int j = 0; j < butonlar[0].length; j++) {
                    if (e.getSource().equals(butonlar[i][j])) {
                        if (sayımlar[i][j] == mayın) {
                            butonlar[i][j].setForeground(Color.red);
                            butonlar[i][j].setText("X");
                            oyunbitti();
                        } else if (sayımlar[i][j] == 0) {
                            butonlar[i][j].setText(sayımlar[i][j] + "");
                            butonlar[i][j].setEnabled(false);
                            ArrayList<Integer> temizle = new ArrayList<Integer>();
                            temizle.add(i * 100 + j);
                            sıfırlarıtemizle(temizle);
                            kazankontrol();
                        } else {
                            butonlar[i][j].setText(sayımlar[i][j] + "");
                            butonlar[i][j].setEnabled(false);
                            kazankontrol();
                        }
                    }
                }
            }

        }
    
    }
    
    
    
    
    
    
    
  
}
