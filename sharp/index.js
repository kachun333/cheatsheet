import Path from "path";
import { readdirSync } from "fs";
import sharp from "sharp";

const targetHeight = 2400;
const targetWidth = 1600;
const folder = `D:/code/cheatsheet/imagemin/build/webp/banners/${targetHeight}x${targetWidth}`;

readdirSync(folder).forEach((file) => {
  const absolutePath = folder + "/" + file;
  const filename = Path.parse(absolutePath).name;
  sharp(absolutePath)
    .resize(targetHeight, targetWidth)
    .toFile(`jpg/banners/${targetHeight}x${targetWidth}/${filename}.jpg`, (err, info) =>
      console.log(err, info)
    );
});
