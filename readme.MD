
Two instance of the application can be run by specific profiles(bob or alice).It start's the app by 
using profile specific properties(application-alice.properties) which contains account_id and service_id of the other server.

./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=alice




Alice's AccountId:ecc2094a-c0b9-4eb4-8f07-c019961aa3f0
Bob's AccountId: 7861d56c-8b65-4ebc-be0b-0e25cf230079


Transaction 1 : bob -> alice (5000)
Transaction 2 : alice -> bob (10000)
Transaction 3 : bob -> alice (2500)

* Run TrustlineApplicationTests test for running above transactions 

Logs : 

Bob's Server log :

2018-09-12 16:42:45.882  INFO 93997 --- [  restartedMain] c.ripple.trustline.TrustlineApplication  : Welcome to Trustline
2018-09-12 16:42:48.442  INFO 93997 --- [nio-8080-exec-2] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-09-12 16:42:48.442  INFO 93997 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-09-12 16:42:48.494  INFO 93997 --- [nio-8080-exec-2] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 52 ms
2018-09-12 16:42:48.717  INFO 93997 --- [nio-8080-exec-2] c.r.t.c.TrustlineTransferController      : accepting transfer from 7861d56c-8b65-4ebc-be0b-0e25cf230079 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 and amount=USD:5000
2018-09-12 16:42:48.718  INFO 93997 --- [nio-8080-exec-2] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance USD:0
2018-09-12 16:42:48.718  INFO 93997 --- [nio-8080-exec-2] c.r.t.s.s.TrustlineTransferServiceImpl   : you were paid USD:5000
2018-09-12 16:42:48.718  INFO 93997 --- [nio-8080-exec-2] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance is USD:5000
2018-09-12 16:42:49.058  INFO 93997 --- [nio-8080-exec-4] c.r.t.c.TrustlineTransferController      : initiated transfer from ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 to 7861d56c-8b65-4ebc-be0b-0e25cf230079 and amount=USD:10000
2018-09-12 16:42:49.058  INFO 93997 --- [nio-8080-exec-4] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance USD:5000
2018-09-12 16:42:49.058  INFO 93997 --- [nio-8080-exec-4] c.r.t.s.s.TrustlineTransferServiceImpl   : paying USD:10000 to 7861d56c-8b65-4ebc-be0b-0e25cf230079
2018-09-12 16:42:49.186  INFO 93997 --- [nio-8080-exec-4] c.r.t.s.s.TrustlineTransferServiceImpl   : successfully sent USD:10000 from ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 to 7861d56c-8b65-4ebc-be0b-0e25cf230079
2018-09-12 16:42:49.186  INFO 93997 --- [nio-8080-exec-4] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance is USD:-5000
2018-09-12 16:42:49.323  INFO 93997 --- [nio-8080-exec-7] c.r.t.c.TrustlineTransferController      : accepting transfer from 7861d56c-8b65-4ebc-be0b-0e25cf230079 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 and amount=USD:2500
2018-09-12 16:42:49.323  INFO 93997 --- [nio-8080-exec-7] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance USD:-5000
2018-09-12 16:42:49.323  INFO 93997 --- [nio-8080-exec-7] c.r.t.s.s.TrustlineTransferServiceImpl   : you were paid USD:2500
2018-09-12 16:42:49.323  INFO 93997 --- [nio-8080-exec-7] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance is USD:-2500

 


Alice's Server log:

2018-09-12 16:42:45.401  INFO 93998 --- [  restartedMain] c.ripple.trustline.TrustlineApplication  : Welcome to Trustline
2018-09-12 16:42:48.043  INFO 93998 --- [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-09-12 16:42:48.043  INFO 93998 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-09-12 16:42:48.067  INFO 93998 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 24 ms
2018-09-12 16:42:48.191  INFO 93998 --- [nio-8081-exec-1] c.r.t.c.TrustlineTransferController      : initiated transfer from 7861d56c-8b65-4ebc-be0b-0e25cf230079 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 and amount=USD:5000
2018-09-12 16:42:48.192  INFO 93998 --- [nio-8081-exec-1] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance USD:0
2018-09-12 16:42:48.192  INFO 93998 --- [nio-8081-exec-1] c.r.t.s.s.TrustlineTransferServiceImpl   : paying USD:5000 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0
2018-09-12 16:42:48.812  INFO 93998 --- [nio-8081-exec-1] c.r.t.s.s.TrustlineTransferServiceImpl   : successfully sent USD:5000 from 7861d56c-8b65-4ebc-be0b-0e25cf230079 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0
2018-09-12 16:42:48.812  INFO 93998 --- [nio-8081-exec-1] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance is USD:-5000
2018-09-12 16:42:49.146  INFO 93998 --- [nio-8081-exec-2] c.r.t.c.TrustlineTransferController      : accepting transfer from ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 to 7861d56c-8b65-4ebc-be0b-0e25cf230079 and amount=USD:10000
2018-09-12 16:42:49.146  INFO 93998 --- [nio-8081-exec-2] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance USD:-5000
2018-09-12 16:42:49.147  INFO 93998 --- [nio-8081-exec-2] c.r.t.s.s.TrustlineTransferServiceImpl   : you were paid USD:10000
2018-09-12 16:42:49.147  INFO 93998 --- [nio-8081-exec-2] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance is USD:5000
2018-09-12 16:42:49.215  INFO 93998 --- [nio-8081-exec-3] c.r.t.c.TrustlineTransferController      : initiated transfer from 7861d56c-8b65-4ebc-be0b-0e25cf230079 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0 and amount=USD:2500
2018-09-12 16:42:49.215  INFO 93998 --- [nio-8081-exec-3] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance USD:5000
2018-09-12 16:42:49.215  INFO 93998 --- [nio-8081-exec-3] c.r.t.s.s.TrustlineTransferServiceImpl   : paying USD:2500 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0
2018-09-12 16:42:49.326  INFO 93998 --- [nio-8081-exec-3] c.r.t.s.s.TrustlineTransferServiceImpl   : successfully sent USD:2500 from 7861d56c-8b65-4ebc-be0b-0e25cf230079 to ecc2094a-c0b9-4eb4-8f07-c019961aa3f0
2018-09-12 16:42:49.326  INFO 93998 --- [nio-8081-exec-3] c.r.t.s.s.TrustlineTransferServiceImpl   : trustline balance is USD:2500

Contract Test :

Producer side contract-test and stubs are created in target(ContractVerifierTest) using contracts in src/test/resources/contracts . 
These are ran as part of the maven build/


