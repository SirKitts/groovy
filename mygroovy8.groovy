import groovy.xml.XmlUtil
import groovy.xml.MarkupBuilder

// def newFile = new File("test.txt")
// newFile.createNewFile()


def xml = new XmlSlurper().parseText( "<foo/>" ) 

xml.appendNode { 
    bar { 
        baz()
    } 
}

println XmlUtil.serialize( xml )

def writer = new StringWriter()
def xml = new MarkupBuilder(writer) 

xml.records() { 
    car(name: 'HSV Maloo', make: 'Holden', year: 2006) {
        country('Australia')
        record(type: 'speed', 'Production Pickup Truck with speed of 271kph')
    }
    car(name: 'Royale', make: 'Bugatti', year: 1931) {
        country('France')
        record(type: 'price', 'Most Valuable Car at $15 million')
    }
}

def records = new XmlSlurper().parseText(writer.toString()) 

assert records.car.first().name.text() == 'HSV Maloo'
assert records.car.last().name.text() == 'Royale'