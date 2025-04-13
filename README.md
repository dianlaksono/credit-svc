# Credit Simulator

## Deskripsi
Aplikasi ini dapat digunakan untuk menghitung simulasi cicilan bulanan untuk pinjaman kendaraan.

## Cara Menjalankan Aplikasi
### Tanpa Input File
1. Buka terminal
2. Navigasikan ke direktori `bin/`
3. Jika menggunakan linux berikan izin eksekusi pada file dengan menggunakan perintah chmod +x bin/credit_simulator
4. Jalankan aplikasi dengan perintah:
   - Windows : credit_simulator.bat
   - Linux : ./credit_simulator

### Menggunakan Input File
1. Buka terminal
2. Navigasikan ke direktori `bin/`
3. Jika menggunakan linux berikan izin eksekusi pada file dengan menggunakan perintah chmod +x bin/credit_simulator
4. Jalankan aplikasi dengan perintah:
    - Windows : credit_simulator.bat ../../src/main/resources/doc/file_inputs.txt
    - Linux : ./credit_simulator ../../src/main/resources/doc/file_inputs.txt

## Cara Menggunakan Aplikasi (tanpa input file)
- Setelah aplikasi berjalan, Anda akan melihat menu dengan opsi berikut:
  1. Hitung simulasi pinjaman
  2. Hitung simulasi pinjaman dari web service (https://run.mocky.io/v3/1c15a428-2a0f-4ec9-b4c3-925698a33d08)
  3. Lihat daftar riwayat perhitungan pinjaman 
  4. Keluar
- Pilih opsi yang diinginkan dengan memasukkan nomor yang sesuai.
- Untuk menghitung cicilan, masukkan informasi sesuai permintaan.
- Setelah menghitung cicilan, Anda dapat menyimpan data pinjaman ke dalam sheet dengan memberikan nama sheet.
- Anda dapat memilih sheet yang telah disimpan untuk melihat data pinjaman yang terkait.

## Catatan
- Pastikan untuk memeriksa validasi input saat memasukkan data kendaraan dan pinjaman.
- Aplikasi ini tidak terhubung ke database, sehingga semua data disimpan dalam memori selama sesi aplikasi.