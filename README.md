# Code assessment task

Есть имя файла, представленное в виде строки, разделенное нижними подчеркиваниями. 
Примеры имен:
overview
electronics_accessoires_overview
electronics_accessoires_phones
electronics_washers
books_detectives
books_professional_it_2020_overview

Необходимо построить древовидное меню на основе этих файлов по следующим правилам - последний элемент является файлом, а все промежуточные - означают вложенность этого файла и их можно представить в  директорий, в которой этот файла находится.

Полученную структуру файлов и директорий вывести в консоль по следующим правилам:
- перед каждой директорией добавляется '/'
- каждый новый уровень вложенности выводится на новой строке и к каждому новому уровню надо добавить табуляцию.
- результат должен быть отсортирован в алфавитном порядке, при этом если на одном уровне находятся и файлы и папки, то файлы должны быть отображены перед папками

Пример ожидаемого результата:
1. input:
[
"electronics_accessoires_overview"
]

outut:
/electronics
	/accessoires
		overview

2. input:
[
"overview"
]

output:
overview

2. input:
[
"electronics_accessoires_overview",
"books_detectives",
"books_professional_it_2020_overview"
]

output:
/books
	detectives
	/professional
		/it
			/2020
				overview
/electronics
	/accessoires
		overview
