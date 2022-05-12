# Uses the node base image with the latest LTS version
FROM node:18

WORKDIR /app

COPY package.json yarn.lock ./
RUN yarn

COPY . ./

EXPOSE 3000
CMD ["yarn", "start"]