import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.ImageGraphicAttribute;
import java.util.Random;

/**
 * Created by 1407268 on 29-12-2018.
 */
public class Gameplay extends JPanel implements KeyListener,ActionListener{

    private int[]  enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private int[]  enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,
            725,750,775,800,825,850};

    private ImageIcon enemyicon;
    private Random random = new Random();
    private int xpos= random.nextInt(34);
    private int ypos = random.nextInt(23);


    private int[] snakexlength = new int[750];
    private int[] snakeylength= new int[750];

    private  int lengthofsnake =3;
    private boolean left= false;
    private boolean right= false;
    private boolean up = false;
    private boolean down= false;


    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon leftmouth;
    private ImageIcon downmouth;

    private Timer timer;
    private int delay = 100;

    private int moves =0;
    private ImageIcon snakeimage;

    private  ImageIcon titleImage;

    private int score=0;
    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer= new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        if(moves == 0)
        {
            snakexlength[2]=50;
            snakexlength[1]=75;
            snakexlength[0]=100;


            snakeylength[2]=100;
            snakeylength[1]=100;
            snakeylength[0]=100;
        }


        g.setColor(Color.white);
        g.drawRect(24, 10,851,55);


        String titleimg = "img/snaketitle.jpg";
        titleImage = new ImageIcon(getClass().getResource(titleimg));
        titleImage.paintIcon(this,g,25,11);


        g.setColor(Color.white);
        g.drawRect(24,74,851,577);


        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);


        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Scores : "+score,780,30);


        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Length : "+lengthofsnake,780,50);


        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("CREATOR : ARUNODAY SINGH",100,50);



        String rightmouthimg = "img/rightmouth.png";
        String leftmouthimg = "img/leftmouth.png";
        String downmouthimg = "img/downmouth.png";
        String upmouthimg = "img/upmouth.png";
        String bodyimg = "img/snakeimage.png";
        String enemyimg = "img/enemy.png";
        rightmouth = new ImageIcon(getClass().getResource(rightmouthimg));
        rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);

        for(int a=0;a<lengthofsnake;a++)
        {
            if(a==0 && right)
            {
                rightmouth = new ImageIcon(getClass().getResource(rightmouthimg));
                rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }


            if(a==0 && left)
            {
                leftmouth = new ImageIcon(getClass().getResource(leftmouthimg));
                leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }

            if(a==0 && up)
            {
                upmouth = new ImageIcon(getClass().getResource(upmouthimg));
                upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }

            if(a==0 && down)
            {
                downmouth = new ImageIcon(getClass().getResource(downmouthimg));
                downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);
            }

            if(a!=0)
            {
                snakeimage = new ImageIcon(getClass().getResource(bodyimg));
                snakeimage.paintIcon(this,g,snakexlength[a],snakeylength[a]);

            }
        }
        enemyicon = new ImageIcon(getClass().getResource(enemyimg));

        for(int x=0;x < lengthofsnake; x++) {
            if ((enemyxpos[xpos] == snakexlength[x] && enemyypos[ypos] == snakeylength[x])) {
                lengthofsnake++;
                score += 10;
                xpos = random.nextInt(34);
                ypos = random.nextInt(23);
            }
        }

        enemyicon.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

        for(int b=1;b<lengthofsnake;b++)
        {
            if((snakexlength[b]== snakexlength[0] && snakeylength[b]==snakeylength[0]))

            {
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("Game Over",300,300);

                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("PRESS SPACE TO RESTART",300,340);

            }
        }

        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {    
        timer.start();
        if(right)
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakeylength[i+1]=snakeylength[i];
            }
            for(int i=lengthofsnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakexlength[i]=snakexlength[i]+25;
                }
                else
                {
                    snakexlength[i]=snakexlength[i-1];
                }
                if(snakexlength[i]>850)
                {
                    snakexlength[i]=25;
                }
            }
            repaint();
        }
        if(left)
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakeylength[i+1]=snakeylength[i];
            }
            for(int i=lengthofsnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakexlength[i]=snakexlength[i]-25;
                }
                else
                {
                    snakexlength[i]=snakexlength[i-1];
                }
                if(snakexlength[i]< 25)
                {
                    snakexlength[i]= 850;
                }
            }
            repaint();
        }
        if(up) {

            for (int i = lengthofsnake - 1; i >= 0; i--) {
                snakexlength[i + 1] = snakexlength[i];
            }
            for (int i = lengthofsnake; i >= 0; i--) {
                if (i == 0) {
                    snakeylength[i] = snakeylength[i] - 25;
                } else {
                    snakeylength[i] = snakeylength[i - 1];
                }
                if (snakeylength[i] < 75) {
                    snakeylength[i] = 625;
                }
            }
            repaint();
        }
        if(down)
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakexlength[i+1]=snakexlength[i];
            }
            for(int i=lengthofsnake;i>=0;i--)
            {
                if(i==0)
                {
                    snakeylength[i]=snakeylength[i]+25;
                }
                else
                {
                    snakeylength[i]=snakeylength[i-1];
                }
                if(snakeylength[i]> 625)
                {
                    snakeylength[i]=75;
                }
            }
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if((!right) && (!left) && (!up) && (!down))
            {moves=0;
                score=0;
                lengthofsnake=3;
                repaint();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            moves++;
            if(!left)
            {
                right=true;
            }
            else
            {
                right=false;
                left=true;
            }
            down=false;
            up= false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            moves++;
            if(!right)
            {
                left=true;
            }
            else
            {
                right=true;
                left=false;
            }
            down=false;
            up= false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            moves++;
            if(!down)
            {
                up=true;
            }
            else
            {
                up=false;
                down=true;
            }
            right= false;
            left=false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            moves++;
            if(!up)
            {
                down=true;
            }
            else
            {
                down=false;
                up=up;
            }
            right= false;
            left=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


