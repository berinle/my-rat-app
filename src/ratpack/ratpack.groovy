import groovy.json.JsonBuilder
import org.ratpackframework.app.Request
import org.ratpackframework.app.Response
import org.ratpackframework.groovy.app.Routing

(this as Routing).with {

	get('/') { Request request, Response response ->
		response.redirect('index.html')
	}

	//maps to customers
	get('/customers') { Request request, Response response ->
        def listOfMaps = getCustomers().collect { [firstName: it.firstName, lastName: it.lastName] }
        def builder = new JsonBuilder(listOfMaps)
        response.setHeader('Content-Type', 'application/json')
        response.text(builder)

	}

}

class Customer {
    String firstName
    String lastName
}

def getCustomers(){
    def list = []
    list << new Customer(firstName: "John", lastName: "Maxwell")
    list << new Customer(firstName: "Linda", lastName: "Fleming")
    list << new Customer(firstName: "Joe", lastName: "Doe")
    list << new Customer(firstName: "Manning", lastName: "Wells")
    list << new Customer(firstName: "Lee", lastName: "Fu")
    list
}
