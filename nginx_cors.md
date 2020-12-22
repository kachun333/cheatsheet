# Config CORS in nginx
1. configure nginx
```
sudo vi /etc/nginx/sites-available/default
```
2. add the code below to the location block for the server block that you are using
3. don't forget to add any additional headers (e.g. `Authorization`) inside every `Access-Control-Allow-Headers`
for e.g. 
```
add_header 'Access-Control-Allow-Headers' 'Authorization, DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type' always ;
```
4. the `always` keyword is added to every add_header statement so that even the HTTP response code is `404`, the CORS config will also works

```
location / {

   # normal server config
   proxy_pass http://localhost:8080; #whatever port your app runs on
   proxy_http_version 1.1;
   proxy_set_header Upgrade $http_upgrade;
   proxy_set_header Connection 'upgrade';
   proxy_set_header Host $host;
   proxy_cache_bypass $http_upgrade;
   
   # wide open CORS config start
   if ($request_method = 'OPTIONS') {
      add_header 'Access-Control-Allow-Origin' '\*' always; 
      #
      # Om nom nom cookies
      #
      add_header 'Access-Control-Allow-Credentials' 'true' always;
      add_header 'Access-Control-Allow-Methods' 'GET, POST, PUT, DELETE, OPTIONS' always;

      #
      # Custom headers and headers various browsers *should* be OK with but aren't
      #
      add_header 'Access-Control-Allow-Headers' 'DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type' always ;

      #
      # Tell client that this pre-flight info is valid for 20 days
      #
      add_header 'Access-Control-Max-Age' 1728000 always;
      add_header 'Content-Type' 'text/plain charset=UTF-8' always;
      add_header 'Content-Length' 0 always;
      return 204;
   }

   add_header 'Access-Control-Allow-Origin' '*' always;
   add_header 'Access-Control-Allow-Credentials' 'true' always;
   add_header 'Access-Control-Allow-Methods' 'GET, POST, PUT, DELETE, OPTIONS' always;
   add_header 'Access-Control-Allow-Headers' 'X-Authorization-Firebase,Authorization,DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type' always;
      #
      # Custom headers and headers various browsers *should* be able to use it.
      # see https://stackoverflow.com/questions/37897523/axios-get-access-to-response-header-fields
      #
   add_header 'Access-Control-Expose-Headers' 'Link, X-Total-Count' always;
   
   # wide open CORS config ends

   }
```

# Apply the configuration
```
# Check NGINX config
sudo nginx -t

# Restart NGINX
sudo service nginx restart
```

# References
1. [Brad Traversy nginx](https://gist.github.com/bradtraversy/cd90d1ed3c462fe3bddd11bf8953a896)
2. [Wide open nginx CORS configuration](https://michielkalkman.com/snippets/nginx-cors-open-configuration/)
