package cn.weiben.buildingshopping.model;

import java.util.List;

public class LeaveMessageBean {


    /**
     * message_list : [{"msg_content":"发撒的撒问请问请问","msg_time":"2020-01-09 11:21:15","msg_type":"询问","msg_title":"大大","message_img":"","order_id":"0"},{"msg_content":"发撒的撒问请问请问","msg_time":"2020-01-09 11:20:46","msg_type":"留言","msg_title":"大大","message_img":"","order_id":"0"},{"msg_content":"发撒的撒","msg_time":"2020-01-09 11:20:37","msg_type":"留言","msg_title":"大大","message_img":"","order_id":"0"},{"msg_content":"","msg_time":"2020-01-09 11:18:58","msg_type":"求购","msg_title":"ewqwe","message_img":"","order_id":"0"},{"msg_content":"","msg_time":"2020-01-09 11:18:47","msg_type":"售后","msg_title":"321321","message_img":"","order_id":"0"}]
     * message_board : 1
     * page : 1
     * page_count : 2
     * order_info : []
     */

    private String message_board;
    private int page;
    private int page_count;
    private List<MessageListBean> message_list;
    private List order_info;

    public String getMessage_board() {
        return message_board;
    }

    public void setMessage_board(String message_board) {
        this.message_board = message_board;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public List<MessageListBean> getMessage_list() {
        return message_list;
    }

    public void setMessage_list(List<MessageListBean> message_list) {
        this.message_list = message_list;
    }

    public List getOrder_info() {
        return order_info;
    }

    public void setOrder_info(List order_info) {
        this.order_info = order_info;
    }

    public static class MessageListBean {
        /**
         * msg_content : 发撒的撒问请问请问
         * msg_time : 2020-01-09 11:21:15
         * msg_type : 询问
         * msg_title : 大大
         * message_img :
         * order_id : 0
         */

        private String msg_content;
        private String msg_time;
        private String msg_type;
        private String msg_title;
        private String message_img;
        private String order_id;

        public String getMsg_content() {
            return msg_content;
        }

        public void setMsg_content(String msg_content) {
            this.msg_content = msg_content;
        }

        public String getMsg_time() {
            return msg_time;
        }

        public void setMsg_time(String msg_time) {
            this.msg_time = msg_time;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getMsg_title() {
            return msg_title;
        }

        public void setMsg_title(String msg_title) {
            this.msg_title = msg_title;
        }

        public String getMessage_img() {
            return message_img;
        }

        public void setMessage_img(String message_img) {
            this.message_img = message_img;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
