package cfranc.ilc;

public class MarkovData {
    String theWord;
    int theCount;

    public MarkovData(String w, int c)
    {
        theWord = w;
        theCount = c;
    }

    public void setTheCount(int c)
    {
        theCount = c;
    }

    public String getTheWord()
    {
        return theWord;
    }

    public int getTheCount()
    {
        return theCount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MarkovData other = (MarkovData) obj;
        if ((this.theWord == null) ? (other.theWord != null) : !this.theWord.equals(other.theWord)) {
            return false;
        }
        if (this.theCount != other.theCount) {
            return false;
        }
        return true;
    }
    

}