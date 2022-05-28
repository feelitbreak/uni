int cmp(const void *A, const void *B)
{
	int a = 1;
	char *str1 = new char[256];
	char *str2 = new char[256];
	str1 = (*(Tarif *)A).name;
	str2 = (*(Tarif *)B).name;
	if (strlen(str1) == strlen(str2) && strcmp(str1, str2) == 0)
		return 0;
	while (strncmp(str1, str2, a) == 0)
		a++;
	return str1[a - 1] < str2[a - 1] ? -1 : 1;
}

qsort(T, v, sizeof(Tarif), cmp);

// Tarif - структура, T - массив структур, A и B - элементы массива, v - размер массива