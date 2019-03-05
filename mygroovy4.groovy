import groovy.beans.Bindable 
import groovy.swing.*
import javax.swing.event.*
import java.awt.*
  
@Bindable
class Address { 
    String street, number, city
    String toString() { "address[street=$street,number=$number,city=$city]" }
}
   
def address = new Address(street: 'Evergreen Terrace', number: '742', city: 'Springfield')
   
def swingBuilder = new SwingBuilder()
swingBuilder.edt {  // edt method makes sure UI is build on Event Dispatch Thread.
    lookAndFeel 'nimbus'  // Simple change in look and feel.
    def fillMenu = { ->
        scripts.removeAll()
        new File('/tmp').listFiles().each {
            scripts.add(menuItem(text: it.name))
        }
    }
    frame(title: 'Testing', 
    	size: [800, 600], 
    	visible:true) {
        menuBar {
            menu(id:'scripts', text: 'External Scripts')
        }  
    }
    scripts.addMenuListener([ menuCanceled: { e -> },
                              menuDeselected: { e -> },
                              menuSelected: { e -> fillMenu() } ] as MenuListener)
}
