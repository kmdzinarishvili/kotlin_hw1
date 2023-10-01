fun main() {
    println("Palindromic Squares between 1 and 1000:  ${getPalindromicSquares(1,1000)}");
    println("Biggest number from 1562912 by removing 2 digits: ${biggestSubset(1562912, 2)}");
    println("Prime Divisors of 100 ${getPrimeDivisors(100)}");
}

//დავალება 1
fun getPalindromicSquares(n:Int, m:Int): ArrayList<Int>{
    if (m<n){
        throw InvalidParamsException("m needs to be greater than or equal to n");
    }
    fun isPalindrome(number: Number): Boolean {
        val string = number.toString();
        val reversedString = string.reversed();
        return string == reversedString;
    }
    val palindromes = arrayListOf<Int>();
    for (i in n..m){
        if (isPalindrome(i) && isPalindrome(i*i)){
            palindromes.add(i);
        }
    }
    return palindromes;
}

//დავალება 2
fun biggestSubset(number: Int, k: Int): Int? {
    if (k>=number.toString().length){
        throw InvalidParamsException("k needs to be less than the number of digits in number");
    }
    if (k<0){
        throw InvalidParamsException("k can't be negative");
    }
    if (number<1){
        throw InvalidParamsException("number has to be a natural number (1+)");
    }
    fun permutationsOfRemovedNumber(arr: ArrayList<Char>, currDepth: Int, fullDepth: Int): ArrayList<ArrayList<Char>>{
        if(currDepth == fullDepth){
            return arrayListOf(arr);
        }
        val permutations = arrayListOf<ArrayList<Char>>();
        for (i in arr.indices){
            val currArray = ArrayList(arr);
            currArray.removeAt(i);
            permutations+= permutationsOfRemovedNumber(currArray, currDepth + 1 , fullDepth);
        }
        return permutations;
    }
    fun arrayToInt (arr:ArrayList<ArrayList<Char>> ): ArrayList<Int>{
        val intArr = arrayListOf<Int>();
        for (i in arr.indices){
            intArr.add(arr[i].joinToString("").toInt());
        }
        return intArr;
    }
    val charArray = ArrayList(number.toString().toList());
    val permsArray = permutationsOfRemovedNumber(charArray, 0, k);
    val intArr = arrayToInt(permsArray);
        return intArr.maxOrNull();

}

//დავალება 3
fun getPrimeDivisors(int:Int):ArrayList<Int> {
    fun isPrime(num: Int): Boolean{
        for (i in 2..num / 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    val primeDivisors = arrayListOf<Int>();
    for (i in 2..int){
        if (int%i ==0 && isPrime(i)){
            primeDivisors.add(i);
        }
    }
    return primeDivisors;
}