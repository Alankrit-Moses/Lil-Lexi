/*
 * AUTHOR: Alankrit Moses and Soumay Agarwal
 * FILE: LilLexiUI.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is the UI class for LilLexi doc editor.
 */
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;


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
	private int size = 16;
	private String style = "";
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
	}
		
	/**
	 * start the editor
	 */
	public void start()
	{	
		Font f = new Font(display,"Helvetica",16,SWT.NONE);
		currentDoc.setFont(f);
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(800,800);

		canvas.addPaintListener(e -> {
			Rectangle rect = shell.getClientArea();
			e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
	        e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
	        e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
	        
	        lexiControl.draw(shell, e, display);
		});
		
        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
            } 
            public void mouseUp(MouseEvent e) {
            } 
            public void mouseDoubleClick(MouseEvent e) {   } 
        });
        
        canvas.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent e) {
        		Composition comp = currentDoc.getComposition();
        		if(e.keyCode == 16777219)
        		{
        			comp.cursorUpdate("left");
        			updateUI();
        		}
        		else if(e.keyCode == 16777220)
        		{
        			comp.cursorUpdate("right");
        			updateUI();
        		}
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
			Composition comp = currentDoc.getComposition();
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
				comp.setScroll(new Integer(slider.getSelection()));
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
		MenuItem insertStyleItem;
		MenuItem times, helvetic, courier;
		MenuItem insertSizeItem;
		MenuItem size1, size2, size3, size4, size5, size6, size7;

		menuBar = new Menu(shell, SWT.BAR);
		
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);
		
		MenuItem fileUndoItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileUndoItem.setText("Undo");
	    
	    MenuItem fileRedoItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileRedoItem.setText("Redo");

	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");

		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		insertMenu = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
		
		insertMenuHeader2 = new MenuItem(menuBar, SWT.CASCADE);
		insertMenu2 = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader2.setText("Font");
		insertMenuHeader2.setMenu(insertMenu2);

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
	    
	    //Creating menu for Font
	    //Adding for font
	    insertStyleItem = new MenuItem(insertMenu2, SWT.CASCADE);
	    insertStyleItem.setText("Style");
	    Menu style = new Menu(shell, SWT.DROP_DOWN);
	    insertStyleItem.setMenu(style);
	    times = new MenuItem(style,SWT.NONE);
	    times.setText("Times New Roman");
	    helvetic = new MenuItem(style, SWT.NONE);
	    helvetic.setText("Helvetica");
	    courier = new MenuItem(style, SWT.NONE);
	    courier.setText("Courier");
	    
	    insertSizeItem = new MenuItem(insertMenu2, SWT.CASCADE);
	    insertSizeItem.setText("Size");
	    Menu size = new Menu(shell, SWT.DROP_DOWN);
	    insertSizeItem.setMenu(size);
	    size1 = new MenuItem(size, SWT.NONE);
	    size1.setText("16");
	    size2 = new MenuItem(size, SWT.NONE);
	    size2.setText("20");
	    size3 = new MenuItem(size, SWT.NONE);
	    size3.setText("24");
	    size4 = new MenuItem(size, SWT.NONE);
	    size4.setText("28");
	    size5 = new MenuItem(size, SWT.NONE);
	    size5.setText("32");
	    size6 = new MenuItem(size, SWT.NONE);
	    size6.setText("36");
	    size7 = new MenuItem(size, SWT.NONE);
	    size7.setText("40");
	    
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
	    fileUndoItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.undo();
	    		updateUI();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });
	    fileRedoItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		lexiControl.redo();
	    		updateUI();
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
					public void widgetDefaultSelected(SelectionEvent e) {}
	    		});
	    apple.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				lexiControl.addImage(image[1]);
				updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}
	
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
	    
	    // FONT
	    
	    times.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, "Times New Roman", data.getHeight(), SWT.NONE);
			    currentDoc.setFont(f);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    helvetic.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, "Helvetic", data.getHeight(), SWT.NONE);
			    currentDoc.setFont(f);
			    updateUI();
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    courier.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, "Courier", data.getHeight(), SWT.NONE);
			    currentDoc.setFont(f);
			    updateUI();
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {}});
	    
	    size1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 16, SWT.NONE);
			    currentDoc.setFontSize(16);
			    currentDoc.setFont(f);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    size2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 20, SWT.NONE);
			    currentDoc.setFont(f);
			    currentDoc.setFontSize(20);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    size3.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 24, SWT.NONE);
			    currentDoc.setFont(f);
			    currentDoc.setFontSize(24);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    size4.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 28, SWT.NONE);
			    currentDoc.setFont(f);
			    currentDoc.setFontSize(28);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    size5.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 32, SWT.NONE);
			    currentDoc.setFont(f);
			    currentDoc.setFontSize(32);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    size6.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 36, SWT.NONE);
			    currentDoc.setFont(f);
			    currentDoc.setFontSize(36);
			    updateUI();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    size7.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FontData fontDatas[] = currentDoc.getFont().getFontData();
			    FontData data = fontDatas[0];
			    Font f = new Font(display, data.getName(), 40, SWT.NONE);
			    currentDoc.setFont(f);
			    currentDoc.setFontSize(40);
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