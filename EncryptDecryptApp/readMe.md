


Source Learn:
https://www.youtube.com/watch?v=aaSck7jBDbw

ENCRYPT & DECRYPT
* Any body who gets a cryptographic key can decrypt what was encrypted with it
* The keystore system prevents that key can leave the device
* TEE is a piece of hardware separated from the android OS
* Your app can make a request to the keystone system to encrypt or decrypt something with the keys it owns

You can see if the app is actually encrypting the string that input from textview. Open Device Explorer then to this directory (data/data/PACKAGE_NAME_APP/files)

To open the Device Explorer, select View > Tool Windows > Device Explorer or click the Device Explorer button in the tool window bar