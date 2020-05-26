import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.SwingUtilities;

public class TabelaGrafica extends JFrame implements ActionListener
{

   private Container tela;
   
   private JTextField numero=new JTextField("0");
   private JLabel digite=new JLabel("Digite aqui");
   private JLabel min=new JLabel("Valor minimo:");
   private JLabel max=new JLabel("Valor maximo:");
   private JLabel med=new JLabel("Média dos valores:");
   private JButton botao=new JButton("Gerar tabela");
   
   private JTable tabela;
   private JScrollPane seguraTabela;
   
   private double media;
   private double menor;
   private double maior;
   private String[][] conteudo;
   private int quantidade;
   private TemperaturaFactory cont=new TemperaturaFactory();
   
   private int telaW;
   private int telaH;
   private int centroW;
   private int centroH;
   
   
   
   public TabelaGrafica(){
   
      super ("TABELA TEMPERATURAS");
      
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      telaW = d.width;
      telaH = d.height;
      centroW = telaW/2;
      centroH = telaH/2;
      
      tela= getContentPane();
      
      criaTabela();
   
      digite.setBounds(centroW-20,centroH-200,100,25);
      numero.setBounds(centroW-50,centroH-150,100,25);
      botao.setBounds(centroW-100,centroH-100,200,25);
      min.setBounds(centroW-100,centroH+215,150,25);
      max.setBounds(centroW-100,centroH+230,150,25);
      med.setBounds(centroW-100,centroH+245,150,25);
      
      botao.addActionListener(this);
       
      tela.add(digite);
      tela.add(numero);
      tela.add(botao);
      tela.add(seguraTabela);
      tela.add(min);
      tela.add(max);
      tela.add(med);
      
      setSize(telaW,telaH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setLayout(null);
      setVisible(true);
   }
   
   public void actionPerformed(ActionEvent e){
      
      seguraTabela.remove(tabela);
      remove(seguraTabela);
      criaTabela();
      add(seguraTabela);
      remove(min);
      remove(max);
      remove(med);
      media= cont.getMedia();
      maior= cont.getMaior();
      menor= cont.getMenor();
      min.setText(min.getText()+" "+menor);
      max.setText(max.getText()+" "+maior);
      med.setText(med.getText()+" "+media);
      min.setBounds(centroW-100,centroH+215,150,25);
      max.setBounds(centroW-100,centroH+230,150,25);
      med.setBounds(centroW-100,centroH+245,150,25);
      tela.add(min);
      tela.add(max);
      tela.add(med);
      
      repaint(); 
      
   }
   
   public void criaTabela(){
      String vet[] = {"Temperatura (ºC)"};
      quantidade = Integer.parseInt(numero.getText());
      conteudo = cont.completaTabela(quantidade, media, maior, menor);
      tabela = new JTable(conteudo,vet);
      seguraTabela = new JScrollPane(tabela);
      seguraTabela.setBounds(centroW-100,centroH,200,200);
      
   }
   
   public static void main(String [] args){
      SwingUtilities.invokeLater (new Runnable (){
         public void run (){
            new TabelaGrafica();
         }
      });
   }
      

}