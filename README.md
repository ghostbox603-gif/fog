# Dense Horror Fog (Forge 1.20.1)

Mod client-side: sương mù dày đặc, tầm nhìn ~14 block, màu xám xanh chết chóc, sương "thở" lên xuống,
thỉnh thoảng có tiếng bước chân / tiếng động từ trong sương.

## Build ra file .jar

### Cách 1 - Tự động trên GitHub (không cần cài gì)
1. Tạo repo mới trên github.com, upload toàn bộ nội dung thư mục này (nhớ cả thư mục `.github`).
2. Vào tab **Actions** -> workflow **Build mod jar** -> chờ ~3-5 phút.
3. Mở lần chạy đó, kéo xuống **Artifacts**, tải `densefog-jar`. Giải nén sẽ ra `densefog-forge-1.20.1-1.0.0.jar`.

### Cách 2 - Build trên máy
Cài JDK 17 và Gradle 8.1.1, rồi trong thư mục này chạy: `gradle build`
File jar nằm ở `build/libs/`.

## Cài đặt
Bỏ file .jar vào thư mục `mods` của Minecraft Forge 1.20.1 (47.x) rồi chơi.
Chỉnh độ dày sương: `config/densefog-client.toml` (fogDistance càng nhỏ càng dày).
