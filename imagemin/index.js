import imagemin from "imagemin";
import imageminWebp from "imagemin-webp";

async function execute() {
  const files = await imagemin(["D:/KMPK个人照/*.{jpg,png}"], {
    destination: "build/webp/2400x1600",
    plugins: [
      imageminWebp({
        preset: "photo",
        resize: { width: 2400, height: 1600 },
        metadata: "none",
      }),
    ],
  });
  console.log(files);
}

execute();
