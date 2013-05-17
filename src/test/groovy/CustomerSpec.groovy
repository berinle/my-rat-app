import co.freeside.betamax.Betamax
import co.freeside.betamax.Recorder
import groovyx.net.http.RESTClient
import net.sf.json.JSON
import org.junit.Rule
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class CustomerSpec extends Specification {

    @Rule Recorder recorder = new Recorder()


    @Betamax(tape="customers success")
    def "test get customers call"(){
        def http = new RESTClient("http://localhost:5050/customers")

        when:
        def resp = http.get([:])
        println resp

        then:
        resp.status == 200
        resp.contentType == 'application/json'
        resp.data instanceof JSON
        resp.data.size() == 5


    }
}