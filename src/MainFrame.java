import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class MainFrame  extends JFrame {
    private Container cp;
    private JButton examplebtn=new JButton("example");
    private JButton encrybtn=new JButton("encryption");
    private JButton clearbtn=new JButton("clear");
    private JButton decrybtn=new JButton("decrypted");
    private JButton exitbtn=new JButton("Exit");
    private JTextField keyjtf=new JTextField("csie");
    private JLabel jlb=new JLabel("Key");
    private JTextArea jtaL=new JTextArea();
    private JTextArea jtaR=new JTextArea();
    private JScrollPane jspL=new JScrollPane(jtaL);
    private JScrollPane jspR=new JScrollPane(jtaR);
    private JPanel jpl=new JPanel(new GridLayout(7,1,3,3));
    private int a=1;


    public MainFrame(){
        init();
    }
    public void init(){
        this.setBounds(10,10,500,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("ＸＯＲ加密器");
        cp=this.getContentPane();
        cp.setLayout(new BorderLayout(3,3));
        jpl.add(examplebtn);
        jpl.add(encrybtn);
        jpl.add(jlb);
        jpl.add(keyjtf);
        jpl.add(clearbtn);
        jpl.add(decrybtn);
        jpl.add(exitbtn);
        jspL.setPreferredSize(new Dimension(200,400));
        jspR.setPreferredSize(new Dimension(200,400));
        jtaL.setLineWrap(true);
        jtaR.setLineWrap(true);
        jtaR.setEditable(false);
        cp.add(jpl,BorderLayout.CENTER);
        cp.add(jspL,BorderLayout.WEST);
        cp.add(jspR,BorderLayout.EAST);
        examplebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaL.setText("The Department of Computer Science and Information Engineering," +
                        " formerly the Department of Information Technology, was established in 2001. " +
                        "The department first offered a Master program only." +
                        " In 2002, the undergraduate program was established." +
                        " The Doctoral program was established in 2006. " +
                        "Each year the department admits about 80 undergraduates," +
                        " 15 graduate students and 3 Ph.D. students.");
            }
        });
        encrybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=jtaL.getText();
                jtaR.setText("");
                String data[]=new String[str.length()];
                int len=data.length;
                String keystr=keyjtf.getText();
                String keydata[]=new String[keystr.length()];
                int len2=keydata.length;
                for(int i=0;i<len2;i++){
                    keydata[i]= toTwo(keystr.charAt(i));
                }
                for(int i=0;i<len;i++){
                    data[i]=toTwo(str.charAt(i));
                }
                int  count=0;
                for(int i=0;i<len;i++) {
                    char last[] = data[i].toCharArray();
                    while (count < len2) {
                        if (i % len2 == count) {
                            for (int j = 0; j < 8; j++) {
                                if (last[j] == keydata[count].charAt(j)) {
                                    last[j] = '0';
                                } else {
                                    last[j] = '1';
                                }
                            }
                            data[i] = new String(String.valueOf((char) toTen(new String(last))));
                        }
                        count++;
                    }
                    System.out.println();
                    count = 0;
                }
                for(int i=0;i<len;i++){
                    jtaR.append(new String(data[i]));
                }
            }
        });
        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaL.setText("");
                jtaR.setText("");
            }
        });
        decrybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=jtaR.getText();
                jtaR.setText("");
                String data[]=new String[str.length()];
                int len=data.length;
                String keystr=keyjtf.getText();
                String keydata[]=new String[keystr.length()];
                int len2=keydata.length;
                for(int i=0;i<len2;i++){
                    keydata[i]= toTwo(keystr.charAt(i));
                }
                for(int i=0;i<len;i++){
                    data[i]=toTwo(str.charAt(i));
                }
                int  count=0;
                for(int i=0;i<len;i++) {
                    char last[] = data[i].toCharArray();
                    while (count < len2) {
                        if (i % len2 == count) {
                            for (int j = 0; j < 8; j++) {
                                if (last[j] == keydata[count].charAt(j)) {
                                    last[j] = '0';
                                } else {
                                    last[j] = '1';
                                }
                            }
                            data[i] = new String(String.valueOf((char) toTen(new String(last))));
                        }
                        count++;
                    }
                    System.out.println();
                    count = 0;
                }
                for(int i=0;i<len;i++){
                    jtaR.append(new String(data[i]));
                }
            }
        });
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public int toTen(String a){
        BigInteger str = new BigInteger(a, 2);
        return Integer.parseInt(str.toString());
    }
    public String toTwo(int a){
        String str = Integer.toBinaryString(a) ;
        if(str.length()<8){
            int b=8-str.length();
            for(int i=0;i<b;i++){
                str="0"+str;
            }
        }
        return str;
    }

}