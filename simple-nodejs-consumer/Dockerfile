FROM node:4
ADD package.json package.json
RUN npm install
ADD . .
EXPOSE 8903
CMD ["node", "simple-nodejs-consumer.js"]