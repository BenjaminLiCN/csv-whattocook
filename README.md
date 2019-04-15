# csv-whattocook
what to cook tonight?

deploy.sh will be executed as post script for Jenkins to deploy the back end.
front-end is deployed on docker's Nginx container.

In the main/resources/static folder, run this script to do hot deploy:
```
npm start
```
Further change to be made:
```
  1. Fix the problem that user must submit 2 files at the same time. done
  2. Beautify the user interface. done
  3. Add unit test. done
  4. Add MySQL, implement register and login functions.
  5. Use message queue.
  6. Enable the dropbox function, each user will have their own space where they can store data.
  
```
