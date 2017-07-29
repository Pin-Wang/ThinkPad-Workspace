package demo;

public class Test
{
    private String tid;
    private String nid;
    private String bid;
 
    public void setTid(String tid)
    {
        this.tid = tid;
    }
 
    public void setNid(String nid)
    {
        this.nid = nid;
    }
 
    public void setBid(String bid)
    {
        this.bid = bid;
    }
 
    public String toString()
    {
        return "tid = " + tid + ", nid = " + nid + ", bid = " + bid;
    }
}