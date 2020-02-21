Fields: 
-fromDate,toDate,policyNumber, insuranceCost, numberOfDays,product,status, paymentStatus, registerDate,client,createdBy;

-numberOfDays = toDate-fromDate

-fromDate>=registerDate +1

-policyNumber is unique = clien.fin+random string(5 numbers)

-status can be ACTIVE,IN_PROGRESS,DEACTIVE.
On register date: ACTIVE,
fromDate until toDate: IN_PROGRESS,
after toDate: DEACTIVE

-paymentStatus can be PAID,UNPAID. It depends on payment module.

-insuranceCost calculation depends on numberOfDays. If:
numberOfDays = 30 then numberOfDays = numberOfDays 
product.firstAmount;
numberOfDays = 60 then numberOfDays = numberOfDays 
product.secondAmount ;
numberOfDays = 90 then numberOfDays = numberOfDays *
product.thirdAmount