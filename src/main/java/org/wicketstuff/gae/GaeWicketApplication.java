package org.wicketstuff.gae;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.page.IPageManager;
import org.apache.wicket.page.IPageManagerContext;
import org.apache.wicket.page.PersistentPageManager;
import org.apache.wicket.pageStore.DefaultPageStore;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.pageStore.IPageStore;
import org.apache.wicket.pageStore.memory.HttpSessionDataStore;
import org.apache.wicket.pageStore.memory.PageNumberEvictionStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.io.WicketObjectStreamFactory;
import org.apache.wicket.util.lang.WicketObjects;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.mycompany.Start#main(String[])
 */
public class GaeWicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public GaeWicketApplication()
	{
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	@Override
	protected void init() {
		super.init();
		
		getResourceSettings().setResourcePollFrequency(null);
		
//		WicketObjects.setObjectStreamFactory(new WicketObjectStreamFactory());
		
		setPageManagerProvider(new DefaultPageManagerProvider(this) {
			
			public IPageManager get(IPageManagerContext pageManagerContext)
			{
				IDataStore dataStore = new HttpSessionDataStore(pageManagerContext, new PageNumberEvictionStrategy(10));
				IPageStore pageStore = new DefaultPageStore(getName(), dataStore,
					getCacheSize());
				return new PersistentPageManager(getName(), pageStore, pageManagerContext);

			}
		});
	}

	@Override
	public String getConfigurationType() {
		return DEPLOYMENT;
	}
	
	
}
