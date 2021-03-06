package jenkins.mvn;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import jenkins.model.GlobalConfigurationCategory;
import jenkins.tools.ToolConfigurationCategory;
import net.sf.json.JSONObject;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.StaplerRequest;

//as close as it gets to the global Maven Project configuration
@Extension(ordinal = 50) @Symbol("maven")
public class GlobalMavenConfig extends GlobalConfiguration {
    private SettingsProvider settingsProvider;
    private GlobalSettingsProvider globalSettingsProvider;

    public GlobalMavenConfig() {
        load();
    }

    @Override
    public ToolConfigurationCategory getCategory() {
        return GlobalConfigurationCategory.get(ToolConfigurationCategory.class);
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        req.bindJSON(this, json);
        return true;
    }

    public void setGlobalSettingsProvider(GlobalSettingsProvider globalSettingsProvider) {
        this.globalSettingsProvider = globalSettingsProvider;
        save();
    }

    public void setSettingsProvider(SettingsProvider settingsProvider) {
        this.settingsProvider = settingsProvider;
        save();
    }

    public GlobalSettingsProvider getGlobalSettingsProvider() {
        return globalSettingsProvider != null ? globalSettingsProvider : new DefaultGlobalSettingsProvider();
    }

    public SettingsProvider getSettingsProvider() {
        return settingsProvider != null ? settingsProvider : new DefaultSettingsProvider();
    }

    public static GlobalMavenConfig get() {
        return GlobalConfiguration.all().get(GlobalMavenConfig.class);
    }

}
