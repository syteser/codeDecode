# Кодер/декодер текстовых файлов
Программа принимает с коммандной строки 4 параметра:
<br>1 - тип - кодировать или дектодировать (code decode)
<br>2 - имя исходного файла (тхт)
<br>3 - имя финального (закодированого) файла
<br>4 - ключевая фраза 1......int длины
<br>
<br>Первый символ исходного файла смещается на первый символ ключа, второй на второй... когда заканчиваются символы ключа, берется сново первый символ ключа, и так далее.
Процесс декодирования происходил аналогичным образом.
