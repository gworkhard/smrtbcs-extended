# smrtbcs-extended
Даны N файлов log. Необходимо за минимально возможное время вычислить распределение возникновения ошибок (ERROR) за каждый час/минуту/. Соответствующие результаты вывести в отдельный файл Statistics.

Пример лог файла (формат дат выбирается самостоятельно)  
2019-01-01T00:12:01.001;ERROR; Ошибка 1 \
2019-01-01T00:12:01.004;ERROR; Ошибка 2 \
2019-01-01T00:12:01.006;ERROR;Ошибка 3 \
2019-01-02T00:13:02.000;WARN;Предупреждение 1 \
2019-01-02T00:13:02.002;ERROR;Ошибка 5 \
2019-01-03T00:14:03.003;ERROR; Ошибка 6\

Таких файлов N (<10). В файле отчета должны быть (примерно) такие записи:  
2019-01-01, 11.00-12.00 Количество ошибок: 3\
2019-01-02, 12.00-13.00 Количество ошибок: 1\
2019-01-03, 14.00-15.00 Количество ошибок: 1\
