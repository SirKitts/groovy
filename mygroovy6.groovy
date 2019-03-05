import groovy.swing.SwingBuilder

def frame
swing = new SwingBuilder()

showAbout = {
    def pane = swing.optionPane(message:'Testing Menus v1.2')
    def dialog = pane.createDialog(frame, 'About Dialog')
    dialog.show()
}

def menuItems = {
    menu(text:'File', mnemonic:'F') {
    	menuItem() {
        	action( name:'Exit', mnemonic:'E', closure:{ System.exit(0) } ) 
        }
    }
    menu(text:'Help', mnemonic:'H') {
        menuItem() {
            action(name:'About', mnemonic:'A', closure: { showAbout() } ) 
        }
    }
}

frame = swing.frame(title:'Main Frame',location:[100,100],size:[500,150]){
    menuBar (menuItems)
    panel {
        label(text: "A label with a message")
    }
}
frame.show()
