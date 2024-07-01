LoadBalancerClient
==================
##### This Client is used to fetch only one instance which has less load factor based on given serviceId
#####   1 .it will not fetch multiple instances.
#####   2.it supports load balance concept
#####   3. Spring Cloud NetFlixRibbon has provided implementation class for interface: LoadBalancerClient, ie RibbonLoadBalancerClient.
#####   4. This Dependency must be added at client/Consumer side that will fetch only one instance from eureka server which has less load factor
### LoadBalancer Work Flow
<pre>
 1.Autowire  LoadBalancerClient at consumer code
 2.Call method choose(ServiceId) [Pass ServiceId of provider application]
 3.Read URI from serviceInstance
 4.Add path to URI that provide URL
 5.Make hhtp call using restTemplate
</pre>
##### CODE Wise Applications Flow
#### 1.Eureka Server
<pre>
Name: LBCEurekaServer
Dep : Eureka Server
&#8594; At starter class : @EnableEurekaServer
&#8594; application.properties
spring.application.name=LBCEurekaServer
server.port=8761
eureka.client.registerWithEureka=false
eureka.client.fetchRegistry=false
 </pre>
 #### 2.Provider application
 <pre>
 Name: LBCGstService-Provider
 Dep: Spring Web ,DiscoveryClient,
 &#8594; At Starter class :@EnableDiscoveryClient
 &#8594; application.properties
#ServiceId
spring.application.name=LBCGstService-Provider
server.port=8081
# Eureka details
eureka.client.service-url.default-zone=http://localhost:8761/eureka
# Instance Id
eureka.instance.instance-id=${spring.application.name}:{random.value}
****) At providr applivcation side we should provider InstanceId using key
      eureka.instance.instance-id=________(HexaDecimal value/any String)
	  Ex: EMP-SERVICE: A45125BC52D
	  ->Here, ${random.value} generates one hexadecimal number
	  ->instance-id is optional if there is only instance for 
	  ->in this case ServiceId and InstanceId are same
 </pre>
 ####  3. Consumer Application
 <pre>
 Name: LBCInvoiceService-Consumer
 Dep: Spring Web,Eureka DiscoveryClient
&#8594; At Starter Class @EnableDiscoveryClient
&#8594; application.properties
 spring.application.name=LBCInvoiceService-Consumer
 server.port=8082
 eureka.client.service-url.defaultZone=http://localhost:8762/eureka
 </pre>
 #### Execution Flow
 <pre>   
 1.Run Eureka Server
 2.Run Provider application(GST) multiple times by changingport number 3 times
 3.Run consumer application(invoice)
 4.Goto Eureka and click consumer 
 5.Enter full url of consumer(refresh multiple times) 
  http://localhsot:8082/invoice/info
 </pre>
 ### Question
 <pre>
 <b>Q)What is the difference between DiscoveryClient and LoadBalancerClient?</b>
 A)DiscoveryClient supports fetching ServiceInstances as List from Eureka but manual load factor checking should be done by programmer, which is not preffered.
  
  LoadBalancerClient will fetch only one ServiceInstance for every request which has less load factor(Automated LoadBalance Concept) at consumer side.
	 
  it is also called as client side loadbalancerclient
  At Server side/provider side run application as multiple instances.
 </pre>
### LoadBalancerClient
![LoadBalancerClient](https://github.com/rammolankula/Microservices/assets/53596726/0a0bad0b-c1f0-4c60-9e67-3eff0b634e8a)
<pre>
  <b>Enter full url of consumer(refresh multiple times) ,each refresh time it will change the provider PORT as below SNAPS</b>
  http://localhsot:8082/invoice/info
</pre>
##### OUTPUT :1
![Output 1 LBC](https://github.com/rammolankula/Microservices/assets/53596726/e349f7ec-ef30-4329-b341-7931eb719a9a)
##### OUTPUT :2
![Output 2 LBC](https://github.com/rammolankula/Microservices/assets/53596726/e23c4430-d463-49e4-bb8d-f377ef667deb)
##### OUTPUT :3
![Output 3 LBC](https://github.com/rammolankula/Microservices/assets/53596726/97480583-2695-46a3-82c4-5b36b90c0314)
