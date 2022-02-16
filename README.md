# STIMA GREEDY NGENGGGGGGGGGG - ENTELLECT 2020 OVERDRIVE

Tugas Besar 1 IF2211 Strategi Algoritma Semester II Tahun 2021/2022 Pemanfaatan Algoritma Greedy dalam Aplikasi Permainan “Overdrive”

## Author - WeTheClown

1. Hana Fathiyah / 13520047
2. Bayu Samudra / 13520128
3. Febryola Kurnia Putri / 13520140

## Requirements

1. [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), JDK 8 recommended and don't forget to set the path (environment variable).
2. [IntelliJ IDEA](https://www.jetbrains.com/idea/)
3. [Node JS](https://nodejs.org/en/download/)

## How To Build

1. Download latest Entellect Challenge 2020 [starter-pack](https://github.com/EntelectChallenge/2020-Overdrive/releases/tag/2020.3.4).
2. Clone this repository, if you didn't.
3. Copy the **java** folder inside **src** folder from this repository, then paste in **starter-pack/starter-bots**.
   (_discalimer : if there is exist java folder inside the starter-bots, erase the existing java folder first_)
4. Open java folder with Intellij IDEA.
5. Open up the "Maven Projects" tab on the right side of the screen. From here go to the **"java-sample-bot > Lifecycle** group and double-click **"Install"**. This will create a .jar file in the folder called **target**. The file will be called "java-sample-bot-jar-with-dependencies.jar".

## How To Run

1. Update game-runner-config.json. Change "player-a" to "./starter-bots/java".
2. Go back to starter-pack folder.
3. Click run.bat

## How To Visualize

1. Download [Entellect Challenge 2020 Visualizer].(https://github.com/Affuta/overdrive-round-runner)
2. Copy your latest match log that you want to visualize from **match-logs** folder to "Matches" folder inside the visualizer folder that you have downloaded.
3. Click start-visualizer.
4. Choose your match log in the visualizer.
5. Have fun!

## Greedy Algorithm

### 1. _Greedy Berdasarkan Point_

Greedy berdasarkan point dalam proses pengambilan keputusan mengoptimalkan nilai poin yang dapat diperoleh dengan menjalankan suatu perintah. Prosesnya, setiap langkah yang akan diambil perlu dipertimangkan manakah dari setiap perintah yang dapat menghasilkan point maksimum sesuai dengan keadaan mobil saat ini.

### 2. _Greedy Berdasarkan Kecepatan_

Pada solusi ini, greedy dilakukan berdasarkan analisis kecepatan pemain. Pada dasarnya, pemain yang menang adalah pemain yang berhasil sampai di finish line pertama kali. Namun, jika kedua pemain mencapai garis finish secara bersamaan, parameter kedua yang menentukan siapa pemenang permainan adalah kecepatan pemain, yaitu pemain yang memiliki kecepatan lebih cepat adalah pemenangnya jika keduanya sampai di waktu yang bersamaan. Maka dari itu, kami melakukan greedy berdasarkan kecepatan pemain. Elemen-elemen greedy yang terdapat pada alternatif ini adalah sebagai berikut.

### 3. _Greedy Berdasarkan Jumlah Obstacle_

Jumlah obstacle dianalisis pada setiap lane. Alternatif solusi yang digunakan terdiri atas tiga hal, yaitu menggunakan lizard, berpindah lane, dan menurunkan kecepatan. Namun, terdapat suatu kasus di mana setiap lane memiliki obstacle. Untuk kasus yang seperti ini, analisis dilakukan dengan menghitung jumlah obstacle beserta akumulasi damage yang mungkin dihasilkan akibat adanya collision dengan obstacle tersebut.

### 4. _Greedy Berdasarkan Power Ups_

Power Ups memberi banyak manfaat bagi pemain. Dengan mengambil power ups yang tersedia pada lane, pemain bisa menggunakannya untuk menyerang lawan ataupun untuk mengamankan dirinya dari obstacle.

### 5. _Greedy Berdasarkan Attact_

Attack dilakukan untuk memperlemah kecepatan lawan. Dengan memberikan attack pada lawan, seperti menumpahkan oil, menembakkan EMP, dan menaruh cybertruck dapat menyebabkan lawan terkena collision dan meningkatkan akumulasi damage miliknya. Damage lawan yang tinggi dapat mengurangi kecepatan miliknya yang membuat kita berpotensi tiba di finish line lebih cepat.

### 6. _Greedy Berdasarkan Fix_

FixingCar dapat digunakan untuk mengurangi damage yang dimiliki oleh kendaraan kita. Pengoptimalan ini dilakukan dengan menjalankan proses FixingCar setiap pemain terkena collision yang mengakibatkan damage.

### 7. _Greedy Berdasarkan Jumlah Damage_

Greedy berdasarkan jumlah damage ini memengaruhi aksi yang akan dilakukan pemain. Bot di-setting dengan sistem perbaikan langsung apabila pemain terkena collision yang mengakibatkan meningkatnya damage. Berdasarkan eksperimen yang telah dilakukan, perbaikan langsung lebih optimal dibandingkan menunggu damage bertambah semakin besar.
