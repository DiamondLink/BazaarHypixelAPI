# BazaarHypixelAPI
This java library allows you to get hypixel skyblock bazaar infos, and players info, using the hypixel api
## Installation
- Download the library from [releases](https://github.com/DiamondLink/BazaarHypixelAPI/releases)
- Create a "libs" folder in youre project's root
- Add the library to youre classpath
- Done !

- Also, you'll need a hypixel api key to use it. You can get one by typing "/api new" in the chat, on hypixel.

## Setup (for developers) (using Windows & Eclipse)

- Run these two commands in PowerShell:
```bash
./gradlew.bat eclipse
```
- In Eclipse, go to Import... > Existing Projects into Workspace and select this project.
- Also, MAKE SURE TO ADD java-json TO CLASSPATH
- And to compile do :
```bash
./gradlew build
```
The jar file will be in build/libs

## Usage
- First, import the library :
```java
import com.bazaarapi.diamondlink.api.BazaarAPI;
import com.bazaarapi.diamondlink.modules.ApiReader;
```
- The api use the json library, you don't have to install it, its bundled in, just import it:
```java
import org.json.*;
```

- Now you can use it ! Here are some cool features :
- Get all bazaar products :
```java
BazaarAPI.getBazaarAPIReader().getBazaarProductsList(apiKey); //hypixel's api key (obtainable by typing "/api new")
```
- Get buy and sell summaries of a product :
```java
BazaarAPI.getBazaarAPIReader().getBuySummary(JSONObject dataProduct);
BazaarAPI.getBazaarAPIReader().getSellSummary(JSONObject dataProduct);
```
Where dataProduct is the datas provided by the hypixel's api for the product, which you can get doing this :
```java
BazaarAPI.getBazaarAPIReader().getBazaarDataProduct(String productID, String apiKey);
```

- You can also get the sell/buy prices, and sell/buy volumes of a product :
```java
BazaarAPI.getBazaarAPIReader().getBuyPrice(JSONObject dataProduct);
BazaarAPI.getBazaarAPIReader().getSellPrice(JSONObject dataProduct);

BazaarAPI.getBazaarAPIReader().getBuyVolume(JSONObject dataProduct);
BazaarAPI.getBazaarAPIReader().getSellVolume(JSONObject dataProduct);
```
- As well as the purse of a player and the current profile :
```java
BazaarAPI.getBazaarAPIReader().getCurrentCoinsOnThePlayerProfile(String uuid, String apiKey)
```
It will detect the profile is playing is playing on according to the last save time for each profiles

The uuid is the player uuid without "-", for example, mine is "eeb204079b664056960d5f781c32e7ee"

- There are also minors features provided by the api, have a look at this [file]( https://github.com/DiamondLink/BazaarHypixelAPI/blob/master/src/main/java/com/bazaarapi/diamondlink/modules/ApiReader.java)

## Credits
By me (DiamondLink), enjoy

