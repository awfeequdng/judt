/**
 * 
 */
package judp;




/**
 * 
 *     
 * 项目名称：judt    
 * 类名称：SocketManager    
 * 类描述：   管理缓存通信对象 
 * 创建人：jinyu    
 * 创建时间：2018年8月25日 上午2:59:22    
 * 修改人：jinyu    
 * 修改时间：2018年8月25日 上午2:59:22    
 * 修改备注：    该类已经不再使用，只是作为例子保留
 * @version     
 *
 */
public class SocketManager {
//	private static SocketManager instance;  
//	LinkedBlockingQueue<CacheSocket>  objClient=new LinkedBlockingQueue<CacheSocket>();
//	
//	LinkedBlockingQueue<CacheSocket> objudtsocket=new LinkedBlockingQueue<CacheSocket>();
//	Map<Object, Object> mapSocket = new HashMap<>();
//	Map<Object, Object> mapClient= new HashMap<>();
//	private ReferenceQueue<? super judpSocket> referenceSocketQueue=new ReferenceQueue<>();
//	private ReferenceQueue<? super judpClient> referenceJClientQueue=new ReferenceQueue<>();
//	ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor(); 
//	private long waitTime=30*1000;
//	private static final Logger logger=Logger.getLogger(SocketManager.class.getName());
//	  private SocketManager (){
//		  startThread();
//		   startGC();
//	  }
//	  
//	   public static synchronized SocketManager getInstance() {  
//		   
//	  if (instance == null) {  
//		
//	     instance = new SocketManager();  
//	  
//	   
//   }  
//	  return instance;  
//   }
//	   
//	   /*
//	    * 定时回收垃圾
//	    * 
//	    */
//	   private void startGC()
//	   {
//	       pool.scheduleWithFixedDelay(new Runnable() {
//
//            @Override
//            public void run() {
//                System.gc();
//                
//            }
//	           
//	       },2*waitTime,waitTime,TimeUnit.MILLISECONDS);
//	   }
//	   
//	   /**
//	    * 启动线程搜集关闭数据
//	    * 
//	    */
//private void startThread()
//{
//	Thread judpclientGC=new Thread(new Runnable() {
//
//		@Override
//		public void run() {
//			startClientGC();
//			//客户端消失，进入底层消失
//			//可能是没有调用关闭
//		}
//		
//	});
//	judpclientGC.setDaemon(true);
//	judpclientGC.setName("judpclientGC");
//	
//	Thread udtclientThead=new Thread(new Runnable() {
//
//		@Override
//		public void run() {
//			startClient();
//			//直接关闭
//		}
//		
//	});
//	udtclientThead.setDaemon(true);
//	udtclientThead.setName("udtclientThead");
//	
//	Thread  judpsocketGC=new Thread(new Runnable() {
//
//		@Override
//		public void run() {
//			startsocketGC();
//		//judpsocket消失
//		//进入底层消失udtThread
//		}
//		
//	});
//	
//	judpsocketGC.setDaemon(true);
//	judpsocketGC.setName("judpsocketGC");
//	
//
//	Thread udtsocketThread=new Thread(new Runnable() {
//
//		@Override
//		public void run() {
//			startUDTSocket();
//			
//		}
//		
//	});
//	udtsocketThread.setDaemon(true);
//	udtsocketThread.setName("udtsocketThread");
//	
//	//
//	judpclientGC.start();
//	udtclientThead.start();
//	judpsocketGC.start();
//	udtsocketThread.start();
//}
//	   /*
//	    * 外层消失
//	    * 
//	    */
//	private void startsocketGC()
//	{
//		 WeakReference<?> k = null;
//		while(true) {
//			try {
//				k = (WeakReference<?>) referenceSocketQueue.remove();
//				if(k==null)
//				{
//					TimeUnit.SECONDS.sleep(5);
//					continue;
//				}
//			} catch (InterruptedException e1) {
//			
//				e1.printStackTrace();
//				continue;
//			}
//			UDTSocket socket=(UDTSocket) mapSocket.remove(k);
//			if(socket!=null)
//			{
//				if(!socket.isClose())
//				{
//					//还没听关闭
//					//还没有调用过，则进入关闭队列
//	  		        //还没有调用，可能存在2种情况，1调用了关闭方法，进入了关闭队列，2没有进入关闭队列；
//	  	            //多次进入的关闭的没有影响
//                    add(socket);
//				}
//			}
//
//	        }  
//	}
//	
//	
//	
//	/*
//	 * 外层消失处理
//	 */
//	private void startClientGC()
//	{
//		
//		 WeakReference<?> k = null;
//			while(true) {
//				
//				try {
//					k = (WeakReference<?>) referenceJClientQueue.remove();
//					if(k==null)
//					{
//						TimeUnit.SECONDS.sleep(5);
//						continue;
//					}
//				} catch (InterruptedException e) {
//					
//					e.printStackTrace();
//					continue;
//				}
//			
//				  UDTClient client=  (UDTClient) mapClient.remove(k);
//				  add(client);
//				  //外层消失，底层进入关闭
//		        }  
//	}
//	
//	/*
//	 * 外层调用过关闭的
//	 */
//	private  void startClient()
//	{
//		while(true)
//		{
//			try
//			{
//			 CacheSocket cacheData=objClient.take();
//			long left=System.currentTimeMillis()-cacheData.start;
//			if(left<waitTime)
//			{
//				TimeUnit.MILLISECONDS.sleep(waitTime-left);
//			}
//			//外层调用关闭缓存时间到
//			  UDTClient client=(UDTClient) cacheData.obj;
//			  if(!client.isClose())
//			  client.shutdown();
//			//  client.getEndpoint().removeSession(client.getSocketID()); 已经添加到shutdown方法中
//			  logger.info("client退出");
//			}
//			catch(Exception ex)
//			{
//				ex.printStackTrace();
//			}
//		}
//	}
//	
//	/*
//	 * 消失进入关闭队列的直接关闭
//	 */
//	private   void startUDTSocket()
//	{
//	
//		while(true)
//		{
//			try
//			{
//			CacheSocket cacheData=objudtsocket.take();
//			long left=System.currentTimeMillis()-cacheData.start;
//			if(left<waitTime)
//			{
//				//先进先出
//				TimeUnit.MILLISECONDS.sleep(waitTime-left);
//				
//			}
//			//
//			UDTSocket udtsocket=(UDTSocket) cacheData.obj;
//			if(udtsocket!=null)
//			{
//				try {
//					udtsocket.close();
//					udtsocket.getReceiver().stop();
//					udtsocket.getSender().stop();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				logger.info("缓存时间到socket物理关闭，socket退出："+udtsocket.getSession().getSocketID());
//				 udtsocket.getEndpoint().removeSession(udtsocket.getSession().getSocketID());
//				 UDTSession session=udtsocket.getSession();
//				 if(session!=null)
//				   session.setActive(false);
//				    //不在发送ack消息
//				 session=null;
//			
//			}
//			  udtsocket=null;
//			}
//			catch(Exception ex)
//			{
//				ex.printStackTrace();
//			}
//		}
//	}
////	 /*
////	  * 关闭
////	  */
////	public void add(judpSocket socket)
////	{
////		try {
////			CacheSocket cache=new CacheSocket();
////		    WeakReference<judpSocket> weakReference = new WeakReference<judpSocket>(socket, referenceSocketQueue);
////			cache.obj=weakReference;
////			objsocket.put(cache);
////		    mapSocket.put(weakReference,socket.socketID);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//	public void add(UDTSocket usocket)
//	{
//		try {
//			CacheSocket cacheData=new CacheSocket();
//			cacheData.obj=usocket;
//			objudtsocket.put(cacheData);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	/*
//	 * 关闭
//	 */
//	public void add(UDTClient rClient)
//	{
//		try {
//			CacheSocket cacheData=new CacheSocket();
//			cacheData.obj=rClient;
//			objClient.put(cacheData);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/*
//	 * 创建judpClient时加入
//	 * 控制外层未关闭client就没有引用了
//	 * 
//	 */
//	public void addGC(judpClient client,UDTClient rClient)
//	{
//		    WeakReference<judpClient> weakReference = new WeakReference<judpClient>(client,referenceJClientQueue);
//		    mapClient.put(weakReference,rClient);//直接用
//	}
//	
//	/*
//	 * 创建judpSocket加入，
//	 * 控制外层未关闭judpSocket就没有引用了
//	 */
//	public void addGC(judpSocket socket,UDTSocket usocket)
//	{
//		    WeakReference<judpSocket> weakReference = new WeakReference<judpSocket>(socket, referenceSocketQueue);
//		   // mapSocket.put(weakReference,usocket.getSession().getSocketID());
//		    //all_UDTSocket.put(usocket.getSession().getSocketID(), usocket);//通过ID保存UDTSocket
//		    //消失时已经没有UDTSocket数据则不用再进入关闭队列，已经关闭了
//		    mapSocket.put(weakReference, usocket);
//		  
//		    
//	}
}
