class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
       int com=Integer.valueOf(getSize()).compareTo(otherArticle.getSize());
        if(com==0){
            return this.getTitle().compareTo(otherArticle.getTitle());
        }
        else{
            return com;
        }
    }
}
