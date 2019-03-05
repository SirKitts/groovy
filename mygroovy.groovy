import groovy.swing.*
import javax.swing.event.*

def swingBuilder = new SwingBuilder()
def frame

showAbout = {
    def pane = swingBuilder.optionPane(message:'Testing Menus v1.2')
    def dialog = pane.createDialog(frame, 'About Dialog')
    dialog.show()
}

def menuItems = {
    menu(text:'File', mnemonic:'F') {
    	menuItem() {
        	action( name:'Exit', mnemonic:'E', closure:{ System.exit(0) } ) 
        }
    }
    menu(id:'scripts', text: 'External Scripts')
    menu(text:'Help', mnemonic:'H') {
        menuItem() {
            action(name:'About', mnemonic:'A', closure: { showAbout() } ) 
        }
    }
}

frame = swingBuilder.edt {
    def fillMenu = { ->
        scripts.removeAll()
        new File('/tmp').listFiles().each {
            scripts.add(menuItem(text: it.name))
        }
    }
    frame(title: 'MyGroovy version 0.01', 
    	size: [800, 600], 
    	location:[100,100],
    	visible:true) {
        menuBar ( menuItems )  
    }
    scripts.addMenuListener([ menuCanceled: { e -> },
                              menuDeselected: { e -> },
                              menuSelected: { e -> fillMenu() } ] as MenuListener)                             
}


