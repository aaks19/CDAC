
class Node
{
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}


class Solution {
    Node sortedMerge(Node head1, Node head2) {
        
        Node head3 = null;
        Node curr = null;
        
        while(head1!=null && head2!=null)
        {
            if(head1.data <= head2.data)
            {
                if(head3 ==null && curr == null)
                {
                    head3 = new Node(head1.data);
                    curr = head3;
                }
                else
                {
                    curr.next = new Node(head1.data);
                    curr = curr.next;
                }
                
                head1 = head1.next;
            }
            else
            {
                if(head3==null && curr == null)
                {
                    head3 = new Node(head2.data);
                    curr = head3;
                }
                else
                {
                    curr.next = new Node(head2.data);
                    curr = curr.next;
                }
                head2 = head2.next;
            }
        }
        
        if(head1 == null)
        {
            while(head2!=null)
            {
                if(head3==null && curr==null)
                {
                    head3 = new Node(head2.data);
                    curr = head3;
                }
                else
                {
                    curr.next = new Node(head2.data);
                    curr = curr.next;
                }
                
                head2 = head2.next;
            }
        }
        
        if(head2 == null)
        {
            while(head1!=null)
            {
                if(head3==null && curr==null)
                {
                    head3 = new Node(head1.data);
                    curr = head3;
                }
                else
                {
                    curr.next = new Node(head1.data);
                    curr = curr.next;
                }
                
                head1 = head1.next;
            }
        }
        return head3;
        
    }
}