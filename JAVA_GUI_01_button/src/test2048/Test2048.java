package test2048;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Mypanel extends JPanel implements ActionListener{
	
	JButton up = new JButton();
	JButton down = new JButton();
	JButton right = new JButton();
	JButton left = new JButton();
	JButton reset = new JButton();
	// 여기까지 위치 키
	
	JButton score = new JButton(); // 얘는 점수판
	
	int size = 4;
	JButton bottons[][] = new JButton[size][size]; // 판 4X4
	int data[][] = new int[size][size];
	Random ran;
	int num = 0; // score
	
	Mypanel(){
		ran = new Random();
		this.setLayout(null); // 이걸로 기본 레이아웃 해제
		score.setText("SCORE : "+num);
		score.setBounds(700, 100, 150, 80);  // 위치와 크기
		score.setBackground(new Color(199,239,223)); // 색깔 설정
		add(score);
		
		up.setText("▲");
		up.setBounds(800, 400, 60, 60);
		up.addActionListener(this);
		add(up);
		
		reset.setText("리셋");
		reset.setBounds(800, 460, 60, 60);
		reset.setBackground(Color.WHITE);
		reset.addActionListener(this);
		add(reset);
		
		right.setText("▶");
		right.setBounds(860, 460, 60, 60);
		right.addActionListener(this);
		add(right);
		
		left.setText("◀");
		left.setBounds(740, 460, 60, 60);
		left.addActionListener(this);
		add(left);
		
		down.setText("▼");
		down.setBounds(800,520,60,60);
		down.addActionListener(this);
		add(down);
		
		for(int i=0;i<size;i++) {
			for(int n=0;n<size;n++) {
				bottons[i][n] = new JButton();
				bottons[i][n].setBounds(100+n*120, 100+i*120, 120, 120);
				bottons[i][n].setBackground(new Color(199,239,223));
				add(bottons[i][n]);
				
			}
		}
		RanNum();
		RanNum();
	}
	
	void Reset() {
		for(int i=0;i<size;i++) {
			for(int n=0;n<size;n++) {
				bottons[i][n].setText("");
				data[i][n] = 0;
			}
		}
		num = 0;
		score.setText("SCORE : "+num);
		RanNum();
		RanNum();
	}
	
	void RanNum() {
		for(int i=0;i<100;i++) {
			int r = ran.nextInt(size);
			int r2 = ran.nextInt(size);
			int r3 = ran.nextInt(10); // 10번에 한 번 4가 나옴
			if(data[r][r2] != 0) { // 이미 채워진 공간엔 랜덤 불가능
				continue;
			}
			
			if(r3 == 1) {
				bottons[r][r2].setText("4");
				data[r][r2] = 4;
				System.out.println("랜덤 : "+r+","+r2);
				break;
			}else {
				bottons[r][r2].setText("2");
				data[r][r2] = 2;
				System.out.println("랜덤 : "+r+","+r2);
				break;
			}
		}
	}
	
	void up(int y, int x) { // up 일때 자리 교체
		for(int i=y-1;i>=0;i--) {
			if(data[i][x] != 0) {
				System.out.println("up!=0");
				int temp = data[y][x];
				data[y][x] = data[i+1][x];
				data[i+1][x] = temp;
				break;
			}
			if(i==0 && data[i][x] == 0) {
				System.out.println("up=0");
				int temp = data[y][x];
				data[y][x] = data[i][x];
				data[i][x] = temp;
			}
		}
	}
	
	void down(int y, int x) { // down 일때 자리 교체
		for(int i=y+1;i<size;i++) {
			if(data[i][x] != 0) {
				System.out.println("down!=0");
				int temp = data[y][x];
				data[y][x] = data[i-1][x];
				data[i-1][x] = temp;
				break;
			}
			if(i == size-1 && data[i][x] == 0) {
				System.out.println("down=0");
				int temp = data[y][x];
				data[y][x] = data[i][x];
				data[i][x] = temp;
			}
		}
	}
	
	void right(int y, int x) { // right 일때 자리 교체
		for(int n=x+1;n<size;n++) {
			if(data[y][n] != 0) {
				System.out.println("right!=0");
				int temp = data[y][x]; // 원래자리 수 저장
				data[y][x] = data[y][n-1]; // 원래자리에 0이 아닌 수의 하나 전 부분과 교체
				data[y][n-1] = temp;
				break;
			}
			if(n == size-1 && data[y][n] == 0) {
				System.out.println("right=0");
				int temp = data[y][x];
				data[y][x] = data[y][n];
				data[y][n] = temp;
			}
		}
	}
	
	void left(int y, int x) {  // left 일때 자리 교체
		for(int n=x-1;n>=0;n--) {
			if(data[y][n] != 0) {
				System.out.println("left!=0");
				int temp = data[y][x];
				data[y][x] = data[y][n+1];
				data[y][n+1] = temp;
				break;
			}
			if(n == 0 && data[y][n] == 0) {
				System.out.println("left=0");
				int temp = data[y][x];
				data[y][x] = data[y][n];
				data[y][n] = temp;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == reset) { // 리셋버튼 누른 경우
			Reset();
			return;
		}
		
		//-----------------------------------------------
		if(e.getSource() == up) { // up 버튼
			for(int i=0;i<size;i++) {
				for(int n=0;n<size;n++) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("up : "+i+","+n);
						up(i,n);
					}
				}
			}
			
			for(int n=0;n<size;n++) {
				for(int i=0;i<3;i++) {
					if(data[i][n] == 0) {
						continue;
					}
					if(data[i][n] == data[i+1][n]) { // 0이 아니면서 위아래 동일한 경우
						data[i][n] = data[i][n]*2;
						num+=data[i][n];
						data[i+1][n] = 0;
						for(int k=size-1;k>i+1;k--) {
							if(data[k][n] == 0) {
								continue;
							}
							int temp = data[k][n];
							data[k][n] = data[k-1][n];
							data[k-1][n] = temp;
						}
					}
				}
			}
		}
		//-------------------------------------
		else if(e.getSource() == down) { // down 버튼
			for(int i=size-1;i>=0;i--) {
				for(int n=0;n<size;n++) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("down : "+i+","+n);
						down(i,n);
					}
				}
			}
			
			for(int n=0;n<size;n++) {
				for(int i=size-1;i>0;i--) {
					if(data[i][n] == 0) {
						continue;
					}
					if(data[i][n] == data[i-1][n]) { // 0이 아니면서 아래위 동일
						data[i][n] = data[i][n]*2;
						num+=data[i][n];
						data[i-1][n] = 0;
						for(int k=i-1;k>0;k--) {
							if(data[k][n] == 0) {
								continue;
							}
							int temp = data[k][n];
							data[k][n] = data[k-1][n];
							data[k-1][n] = temp;
						}
					}
				}
			}
		}
		//----------------------------------------------
		else if(e.getSource() == right) { // right 버튼
			for(int i=0;i<size;i++) {
				for(int n=size-1;n>=0;n--) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("right : "+i+","+n);
						right(i,n);
					}
				}
			}
			for(int i=0;i<size;i++) {
				for(int n=size-1;n>0;n--) {
					if(data[i][n] == 0) { // 빈칸이면 넘어가고
						continue;
					}
					if(data[i][n] == data[i][n-1]) { // 0이 아니면서 오른쪽 왼쪽 동일
						data[i][n] = data[i][n]*2;
						num+=data[i][n]; // 추가 점수
						data[i][n-1] = 0;
						for(int k=n-1;k>0;k--) {
							if(data[i][k] == 0) {
								continue;
							}
							int temp = data[i][k];
							data[i][k] = data[i][k-1];
							data[i][k-1] = temp;
						}
					}
				}
			}
			
		}
		//--------------------------------------------
		else if(e.getSource() == left) { // left 버튼
			for(int i=0;i<size;i++) {
				for(int n=0;n<size;n++) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("left : "+i+","+n);
						left(i,n);
					}
				}
			}
			for(int i=0;i<size;i++) {
				for(int n=0;n<size-1;n++) {
					if(data[i][n] == 0) {
						continue;
					}
					if(data[i][n] == data[i][n+1]) { // 0이 아니면서 왼쪽 오른쪽 동일
						data[i][n] = data[i][n]*2;
						num+=data[i][n];
						data[i][n+1] = 0;
						for(int k=size-1;k>n+1;k--) {
							if(data[i][k] == 0) {
								continue;
							}
							int temp = data[i][k];
							data[i][k] = data[i][k-1];
							data[i][k-1] = temp;
						}
					}
				}
			}
		}
		//--------------------------------------
		RanNum();
		score.setText("SCORE : "+num);
		
		for(int i=0;i<size;i++) {  // 판에 표기
			for(int n=0;n<size;n++) {
				if(data[i][n] == 0) {
					bottons[i][n].setText("");
				}else {
					bottons[i][n].setText(Integer.toString(data[i][n]));
				}
			}
		}
	}
	
}

public class Test2048 {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("2048"); // frame의 제목
		frame.setBounds(0,0,1000,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // 시각화
		Mypanel mp = new Mypanel();
		frame.setContentPane(mp); // frame 과 Mypanel을 연결
		frame.revalidate();  // 새로고침 역활

	}

}
