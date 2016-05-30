<a href="https://opensource.org/licenses/Apache-2.0" target="_blank"><img src="https://img.shields.io/badge/License-Apache_v2.0-blue.svg?style=flat"/></a>[ ![Download](https://api.bintray.com/packages/rohitshampur/maven/OkhttpHelper/images/download.svg) ](https://bintray.com/rohitshampur/maven/OkhttpHelper/_latestVersion)<a href="http://developer.android.com/index.html" target="_blank"><img src="https://img.shields.io/badge/platform-android-green.svg"/></a> <a href="https://android-arsenal.com/api?level=14" target="_blank"> <a href="https://android-arsenal.com/api?level=14" target="_blank"><img src="https://img.shields.io/badge/API-15%2B-green.svg?style=flat"/></a>
<a href='https://bintray.com/rohitshampur/maven/OkhttpHelper/view?source=watch' alt='Get automatic notifications about new "OkhttpHelper" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>
# OkhttpHelper
Helper class for okhttp library

###Usage
#####gradle dependency
Add below dependency to build.gradle

```
repositories {
    jcenter()
}

dependencies {
    //no need to add okhttp dependency its already added in this library
    compile 'com.rohitshampur.okhttphelper:okhttphelper:1.0.0'
}
```

#####Maven dependency

Add this dependency to pom.xml
```
<dependency>
  <groupId>com.rohitshampur.okhttphelper</groupId>
  <artifactId>okhttphelper</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

Now start using the library like this

**Example** :
```
public class MainActivity extends AppCompatActivity {
    
    private OkHttpClient client;
    private OkHttpHelper helper;
    private String URL="http://www.google.com"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();
        //give an instance of okhttp and a boolean to instruct the library to log the urls
        helper = new OkHttpHelper(client,false);
        //NOTE : Do call this methods in background thread or asynctask
        httpGetMethod();
        httpGetMethodWithParams();
        httpPostMethodWithJsonBody();
        httpPostMethodWithFileUploadAndJsonBody();
        httpPostMethodWithFileUpload();
       
    }
    
    public void httpGetMethod(){
         //in background thread call like below
                         try {
                             String response = helper.httpGet(URL);
                             Log.d(TAG,"response from server = "+response);
                 
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
    }
    public void httpGetMethodWithParams(){
             //in background thread call like below
                      Map<String,String > params = new HashMap<>();
                      params.put("email","test@gmail.com");
                             try {
                                 String response = helper.httpGet(URL,params);
                                 Log.d(TAG,"response from server = "+response);
                     
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
        }
        
    public void httpPostMethodWithJsonBody(){
                 //in background thread call like below
                          JSONObject obj = new JSONObject();
                          obj.put("slno","123");
                          String jsonString = obj.toString();
                                 try {
                                     String response = helper.httpPost(URL,jsonString);
                                     Log.d(TAG,"response from server = "+response);
                         
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
            }
            
    public void httpPostMethodWithFileUploadAndJsonBody(){
                     //in background thread call like below
                              JSONObject obj = new JSONObject();
                              obj.put("slno","123");
                              File file = new File();
                              String filename = "image.jpg"
                              String jsonString = obj.toString();
                                     try {
                                         String response = helper.httpUpload(URL,filename,file,jsonString);
                                         Log.d(TAG,"response from server = "+response);
                             
                                     } catch (IOException e) {
                                         e.printStackTrace();
                                     }
                }
    
    public void httpPostMethodWithFileUpload(){
                         //in background thread call like below
                                  File file = new File();
                                  String filename = "image.jpg"
                                  String jsonString = obj.toString();
                                         try {
                                             String response = helper.httpUpload(URL,filename,file);
                                             Log.d(TAG,"response from server = "+response);
                                 
                                         } catch (IOException e) {
                                             e.printStackTrace();
                                         }
                    }
        
}

```



###License

Copyright &copy; 2016 Rohit Shampur(rohitshampur)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


