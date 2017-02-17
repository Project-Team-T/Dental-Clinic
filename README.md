# Dental-Clinic

How to build:
into the folder Dental_Clinic, run the following command line:
$: gradle build.
Then a jar file will be generated in the folder build/libs
then run this jar file:
$ java -jar build/libs/Dental-Clinic-0.1.0.jar

###interface:
add a new dentist:
http://localhost:8080/dentist/add?Lname=Fan&Fname=Anger&Gender=Female&Phonenumber=08976435&Email=12345678@gmail.com&Address=null&AdditionalInformation=Dr&Image=url


Check all the dentist:
http://localhost:8080/dentist/all 
