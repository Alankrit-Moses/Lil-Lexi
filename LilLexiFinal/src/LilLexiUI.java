/**
 * UI for Lil Lexi
 * 
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.graphics.Font;


/**
 * LilLexiUI
 * 
 */
public class LilLexiUI
{
	private LilLexiDoc currentDoc;
	private LilLexiControl lexiControl;
	private Display display;
	private Shell shell;
	private Label statusLabel;
	private Canvas canvas;
	private int dragOffset;
	static int size = 30;
	Image[] image;
	
	/**
	 * Ctor
	 */
	public LilLexiUI() 
	{
		//---- create the window and the shell
		Display.setAppName("Lil Lexi");
		display = new Display();
		shell = new Shell(display);
	    shell.setText("Lil Lexi");
		shell.setSize(900,900);
		shell.setLayout( new GridLayout());
		image = new Image[3];
		image[0] = new Image(display,"images/duck.jpg");
		image[1] = new Image(display,"images/apple.jpg");
		image[2] = new Image(display,"images/blank.jpg");
		dragOffset = 0;
	}
		
	/**
	 * start the editor
	 */
	public void start()
	{	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(800,800);

		canvas.addPaintListener(e -> new Draw(shell, e, currentDoc, display, currentDoc.getFontSize(), display, dragOffset));
		
        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
            } 
            public void mouseUp(MouseEvent e) {
            } 
            public void mouseDoubleClick(MouseEvent e) {
            	LilLexiUI.size-=20;            } 
        });
        
        canvas.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent e) {
        		System.out.println("KeyCode: "+e.keyCode);
        		if(e.keyCode == 16777221)	//
        			currentDoc.setFontSize(currentDoc.getFontSize()+10);
        		else if(e.keyCode == 16777222)	//16777219
        			currentDoc.setFontSize(currentDoc.getFontSize()-10);
        		else if(e.keyCode == 16777219)
        			currentDoc.cursorUpdate("left");
        		else if(e.keyCode == 16777220)
        			currentDoc.cursorUpdate("right");
        		else if(e.keyCode == 16777217)
        			currentDoc.cursorUpdate("up");
        		else if(e.keyCode == 16777218)
        			currentDoc.cursorUpdate("down");
        		if(e.keyCode==13)
        			lexiControl.add('\n');
        		else if(e.keyCode==9)
        			lexiControl.add('\t');
        		else if(e.keyCode == 8){
        			lexiControl.remove();
        		}
        		else if(e.keyCode>=0 && e.keyCode<=200) {
	        		lexiControl.add(e.character);
	        	}
    			updateUI();
        	}
        	public void keyReleased(KeyEvent e) {}
        });

		Slider slider = new Slider (canvas, SWT.VERTICAL);
		Rectangle clientArea = canvas.getClientArea();
		slider.setBounds (clientArea.width - 40, clientArea.y + 10, 32, clientArea.height);
		slider.addListener (SWT.Selection, event -> {
			String string = "SWT.NONE";
			switch (event.detail) {
				case SWT.DRAG: string = "SWT.DRAG"; break;
//				case SWT.HOME: string = "SWT.HOME"; break;
//				case SWT.END: string = "SWT.END"; break;
//				case SWT.ARROW_DOWN: string = "SWT.ARROW_DOWN"; break;
//				case SWT.ARROW_UP: string = "SWT.ARROW_UP"; break;
//				case SWT.PAGE_DOWN: string = "SWT.PAGE_DOWN"; break;
//				case SWT.PAGE_UP: string = "SWT.PAGE_UP"; break;
			}
			if(string.equals("SWT.DRAG"))
			{
				int offset = new Integer(slider.getSelection());
				dragOffset = offset;
				updateUI();
			}
		});
		        
        //---- status label
        lowerComp.setLayout(new RowLayout());
        statusLabel = new Label(lowerComp, SWT.NONE);		

		FontData[] fD = statusLabel.getFont().getFontData();
		fD[0].setHeight(24);
		statusLabel.setFont( new Font(display,fD[0]));
		statusLabel.setText("Ready to edit!");
		
		//---- main menu
		Menu menuBar, fileMenu, insertMenu, insertMenu2, helpMenu;
		MenuItem insertImageItem;
		MenuItem fileMenuHeader, insertMenuHeader, insertMenuHeader2, helpMenuHeader, fileExitItem, fileSaveItem, helpGetHelpItem;
		MenuItem insertRectItem;
		MenuItem duck,apple,question;
		MenuItem insertShapeItem;
		MenuItem rectangle,circle;

		menuBar = new Menu(shell, SWT.BAR);
		
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		
		fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("Save");

	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");

		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
//		insertMenuHeader2 = new MenuItem(menuBar, SWT.PUSH);
//		insertMenu2 = new Menu(shell, SWT.DROP_DOWN);
//		insertMenuHeader2.setMenu(insertMenu2);

	    insertImageItem = new MenuItem(insertMenu, SWT.CASCADE);
	    insertImageItem.setText("Image");
	    Menu img = new Menu(shell,SWT.DROP_DOWN);
	    insertImageItem.setMenu(img);
	    duck = new MenuItem(img, SWT.NONE);
	    duck.setText("Duck");
	    apple = new MenuItem(img, SWT.NONE);
	    apple.setText("Apple");
	    question = new MenuItem(img, SWT.PUSH);
	    question.setText("Question");
//	    
//	    insertShapeItem = new MenuItem(insertMenu, SWT.CASCADE);
//	    insertImageItem.setText("Shapes");
//	    Menu shape = new Menu(shell,SWT.DROP_DOWN);
//	    insertShapeItem.setMenu(shape);
//	    rectangle = new MenuItem(shape, SWT.NONE);
//	    rectangle.setText("Rectangle");
//	    circle = new MenuItem(shape, SWT.NONE);
//	    circle.setText("Circle");
	    
	    
	    insertRectItem = new MenuItem(insertMenu, SWT.CASCADE);
	    insertRectItem.setText("Shapes");
	    Menu shape = new Menu(shell,SWT.DROP_DOWN);
	    insertRectItem.setMenu(shape);
	    rectangle = new MenuItem(shape, SWT.NONE);
	    rectangle.setText("Rectangle");
	    circle = new MenuItem(shape, SWT.NONE);
	    circle.setText("Circle");

	    helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("Help");
	    helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("Get Help");
	    
	    fileExitItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		shell.close();
	    		display.dispose();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		shell.close();
	    		display.dispose();
	    	}
	    });
	    fileSaveItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });

	    insertImageItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });	
	    
	    duck.addSelectionListener(new SelectionListener(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						lexiControl.addImage(image[0]);
						updateUI();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
						// TODO Auto-generated method stub
						
					}
	    	
	    		});
	    apple.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				lexiControl.addImage(image[1]);
				updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	
		});
	    question.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				lexiControl.addImage(image[2]);
				updateUI();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
	
		});
	    
	    rectangle.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				lexiControl.drawShape("rectangle");
				updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
	    	
	    });
	    
	    circle.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				lexiControl.drawShape("circle");
				updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    

//        Menu systemMenu = Display.getDefault().getSystemMenu();
//        MenuItem[] mi = systemMenu.getItems();
//        mi[0].addListener(SWT.Selection, new Listener() {
//            public void handleEvent(Event event){
//            	System.out.println("About");
//            }
//        });
	    
	    shell.setMenuBar(menuBar);
	      
		//---- event loop
		shell.open();
		while( !shell.isDisposed())
			if(!display.readAndDispatch()){}
		display.dispose();
	} 

	/**
	 * updateUI
	 */
	public void updateUI()
	{
		canvas.redraw();
	}
	
	/**
	 * setCurrentDoc
	 */
	public void setCurrentDoc(LilLexiDoc cd) { currentDoc = cd; }
	/**
	 * setController
	 */
	public void setController(LilLexiControl lc) { lexiControl = lc; }
}